package abiz.ir.crwlr;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Logger {
    static String FORMAT = "%6d [%10s] %6d";
    ExecutorService service;

    public Logger() {
        service = Executors.newFixedThreadPool(100);
    }

    static void log(String url) {

    }

}
