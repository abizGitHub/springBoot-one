package abiz.ir.crwlr;

import java.io.*;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {
    static String FORMAT = "%-5d <<<|%-100s|>>> %-5d";
    static ExecutorService service;
    static OutputStreamWriter outputStreamWriter;
    static Lock lock;
    static String addr = "./src/main/java/abiz/ir/crwlr/a";

    public static String extractUrl(String line) {
        return line.replaceFirst("[0-9]*\\s*<<<[|]", "").replaceFirst("[|]>>> [0-9]*\\s*", "").trim();
    }

    public static String makeLine(String url, long id, Long parentId) {
        return String.format(FORMAT, id, url, parentId);
    }

    public static String extractBaseUrl(String url) {
        Matcher matcher = Pattern.compile("http[s]?://[^/]*").matcher(url);
        if (matcher.find())
            return matcher.toMatchResult().group();
        return url;
    }

    static {
        service = Executors.newFixedThreadPool(100);
        try {
            File f = new File(addr);
            if (f.exists()) f.delete();
            File file = new File(addr);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.flush();
            fileWriter.close();
            lock = new ReentrantLock();
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void log(String url, Long parentId, Long id) {
        service.submit(new Writer(url, parentId, id));
    }

    static class Writer implements Runnable {

        final String log;
        final Long parentId;
        final Long id;

        public Writer(String log, Long parentId, Long id) {
            this.log = log;
            this.parentId = parentId;
            this.id = id;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                outputStreamWriter.write(makeLine(log, id, parentId) + "\n");
                outputStreamWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }

    }

    public static void main(String[] args) {
  /*      Logger.log("https://www.theguardian.com/international", 125L);
        Logger.log("https://www.theguardian.com/international/aa", 89L);
        Logger.log("https://www.theguardian.com/international/b", 154L);
        Logger.log("https://www.theguardian.com/international/cccd", 0L);
  */
        String u = "https://support.theguardian.com/support";
        //u.indexOf("")
        Matcher matcher = Pattern.compile("http[s]?://[^/]*").matcher(u);
        if (matcher.find())
            System.out.println(matcher.toMatchResult().group());
        System.out.println(extractBaseUrl(u));
        System.out.println(extractBaseUrl("https://www.theguardian.com/international/cccd"));

    }
}
