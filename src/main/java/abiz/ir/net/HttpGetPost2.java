package abiz.ir.net;


import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
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

public class HttpGetPost2 {

    static AtomicLong sera;
    static AtomicLong dora;
    static ReentrantLock lock = new ReentrantLock();
    static int timeInterval = 100;

    static String sample_query = "{\"EstelamType\":\"VehicleMeli\",\"MotorNo\":\"\",\"ChassisNo\":\"\",\"NationalId\":\"\",\"Plk1_VehicleMeli\":\"DDDDD\",\"Plk2\":\"Mim\",\"Plk3\":\"SSSSS\",\"Plksrl_VehicleMeli\":\"36\",\"Plk1_VehicleMeliMotor\":\"\",\"Plksrl_VehicleMeliMotor\":\"\",\"Plk\":\"\",\"VIN\":\"\",\"UniqeCode\":\"\",\"PolicyNo\":\"\"}";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("cookie:");
        String cookie = scanner.nextLine();
        System.out.println("shorou az seh ragham plak:");
        sera = new AtomicLong(scanner.nextLong());// 10149866837L
        dora = new AtomicLong(10L);
        System.out.println("time Interval sec (60 is 1 min):");
        timeInterval = scanner.nextInt() * 1000;
        String url = "https://totalapp.dana-insurance.ir/Omumi/EstelamBimeNameKhesarat/GetDataEstelamPolicy";
        //String cookie = "ASP.NET_SessionId=lvmmsz1xljs3kfwz23fpp5ub; TotalApp=AAD43A03BDFCEBD12023D4A34C9C1FFB20B18CA2BF7FF1FBC16AA5CEA7F37A2608E992DBBD659FBF74719274881019E540C6E2FA93A3D234275EF79C50C16B3432E05660BE53FFD9702C4F5D47F04BD7440714A30B3549D087AD5E2A71009F1C";
        String queryString = "type=9&extendedData=" + sample_query;
        String referer = "https://totalapp.dana-insurance.ir/Omumi/EstelamBimeNameKhesarat";
        FileWriter fileWriter = new FileWriter(new File(new Date().getTime() + "_" + dora + "-" + sera + ".txt"));
        fileWriter.append("------- start from " + sera + " ------------");
        fileWriter.append("\n");
        fileWriter.flush();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future> futureList = new ArrayList();
        for (int iii = 0; iii < 1000; iii++) {
            long x = sera.getAndIncrement();
            for (int i = 10; i < 100; i++) {
                int finalI = i;
                Future<?> future = executorService.submit(() -> {
                    try {
                        System.out.println("seh ragham pelak:" + x + " ,do ragham :" + finalI);
                        getHttps(url, cookie, queryString.replaceFirst("SSSSS", x + "").replaceFirst("DDDDD", finalI + ""), referer, fileWriter);
                        Thread.sleep(timeInterval);
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
                Thread.sleep(timeInterval);
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
