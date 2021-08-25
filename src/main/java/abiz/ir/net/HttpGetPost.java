package abiz.ir.net;


import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class HttpGetPost {

    static AtomicLong start;
    static ReentrantLock lock = new ReentrantLock();

    static String sample_query = "{\"EstelamType\":\"UniqeCode\",\"MotorNo\":\"\",\"ChassisNo\":\"\",\"NationalId\":\"\",\"Plk1_VehicleMeli\":\"\",\"Plk2\":0,\"Plk3\":\"\",\"Plksrl_VehicleMeli\":\"\",\"Plk1_VehicleMeliMotor\":\"\",\"Plksrl_VehicleMeliMotor\":\"\",\"Plk\":\"\",\"VIN\":\"\",\"UniqeCode\":\"QQQQQ\",\"PolicyNo\":\"\"}";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("cookie:");
        String cookie = scanner.nextLine();
        System.out.println("start from yekta Code:");
        start = new AtomicLong(scanner.nextLong());// 10149866837L
        String url = "https://totalapp.dana-insurance.ir/Omumi/EstelamBimeNameKhesarat/GetDataEstelamPolicy";
        //String cookie = "ASP.NET_SessionId=lvmmsz1xljs3kfwz23fpp5ub; TotalApp=AAD43A03BDFCEBD12023D4A34C9C1FFB20B18CA2BF7FF1FBC16AA5CEA7F37A2608E992DBBD659FBF74719274881019E540C6E2FA93A3D234275EF79C50C16B3432E05660BE53FFD9702C4F5D47F04BD7440714A30B3549D087AD5E2A71009F1C";
        String queryString = "type=9&extendedData=" + sample_query;
        String referer = "https://totalapp.dana-insurance.ir/Omumi/EstelamBimeNameKhesarat";
        FileWriter fileWriter = new FileWriter(new File(new Date().getTime() + "_" + start + "_" + ".txt"));
        fileWriter.append("------- start from " + start + " ------------");
        fileWriter.append("\n");
        fileWriter.flush();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future> futureList = new ArrayList();
        for (int iii = 0; iii < 100000; iii++) {
            for (int i = 0; i < 10; i++) {
                Future<?> future = executorService.submit(() -> {
                    try {
                        long x = start.getAndIncrement();
                        System.out.println(" get data for " + x);
                        getHttps(url, cookie, queryString.replaceFirst("QQQQQ", x + ""), referer, fileWriter);
                    } catch (Exception e) {
                        System.out.println("--------------error---------------");
                        e.printStackTrace();
                        System.out.println("----------------------------------");
                    }
                });
                futureList.add(future);
            }
            boolean unDone;
            do {
                unDone = futureList.stream().filter(future -> !future.isDone()).count() > 3;
                Thread.sleep(100);
            } while (unDone);
        }

        fileWriter.close();
        executorService.shutdown();
    }


    public static void getHttps(String url, String cookie, String queryString, String referer, FileWriter fileWriter) throws Exception {

        HttpsURLConnection urlConnection = (HttpsURLConnection) new URL(url + "?" + queryString).openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("referer", referer);
        urlConnection.setRequestProperty("content-type", "application/json; charset=utf-8");
        urlConnection.setRequestProperty("accept", "application/json, text/javascript, */*; q=0.01");
        urlConnection.setRequestProperty("cookie", cookie);
        urlConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
        InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
        BufferedReader reader = new BufferedReader(streamReader);
        lock.lock();
        while (reader.ready()) {
            String line = reader.readLine();
            fileWriter.append(line);
            fileWriter.append("\n");
            fileWriter.flush();
        }
        lock.unlock();
    }

}
