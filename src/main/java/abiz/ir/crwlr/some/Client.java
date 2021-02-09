package abiz.ir.crwlr.some;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 1; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        URLConnection urlConnection = new URL("http://127.0.0.1:" + AppServer.port).openConnection();
                        InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
                        char[] chars = new char[1024];
                        while (reader.read(chars) != -1) {
                            for (char c : chars) {
                                System.out.print(c);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        executorService.shutdown();

    }
}
