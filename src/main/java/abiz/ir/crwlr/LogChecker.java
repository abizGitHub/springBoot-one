package abiz.ir.crwlr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LogChecker {
    static File file;

    static {
        file = new File(Logger.addr);
    }


    public static boolean isExactlyInLog(String url) {
        return search(url, true);
    }

    public static boolean isPartlyInLog(String url) {
        return search(url, false);
    }

    private static boolean search(String url, boolean exact) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            try {
                String s;
                while ((s = randomAccessFile.readLine()) != null) {
                    String lineUrl = Logger.extractUrl(s);
                    boolean find = exact ? lineUrl.equals(url) : lineUrl.startsWith(url);
                    if (find) return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {
        String url = "https://www.theguardian.com/international";
        System.out.println(url);
        System.out.println(Logger.makeLine(url, 11, 875048040L));
        System.out.println(Logger.extractUrl(Logger.makeLine(url, 11, 875048040L)));
        for (int i = 0; i < 5000; i++) {
            Logger.log(url, 555L,0L);
        }
        /*try {
            Thread.sleep(111);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(LogChecker.isExactlyInLog(url));
    }

}
