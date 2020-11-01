package abiz.ir.fileReadWrite;


import lombok.SneakyThrows;

import java.io.*;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StringChecker extends Thread {

    String fileName;
    File file;
    static String addr = "./src/main/java/abiz/ir/fileReadWrite/a";
    static Lock lock;
    static Condition write;
    static OutputStreamWriter outputStreamWriter;
    static long id;

    public static void main(String[] args) {
        StringChecker stringChecker = new StringChecker(addr);
        for (int i = 0; i < 100; i++) {
            new Thread(stringChecker.new ContinuousWriter()).start();
            new Thread(stringChecker.new ContinuousWriter()).start();
            new Thread(stringChecker.new ContinuousWriter()).start();
            new Thread(stringChecker.new ContinuousWriter()).start();
            new Thread(stringChecker.new ContinuousWriter()).start();
        }
        stringChecker.start();
    }

    public StringChecker(String fileName) {
        try {
            File f = new File(fileName);
            if (f.exists()) f.delete();
            this.fileName = fileName;
            file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.flush();
            fileWriter.close();
            lock = new ReentrantLock();
            write = lock.newCondition();
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        RandomAccessFile randomAccessFile;
        while (true) {
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                String s;
                while ((s = randomAccessFile.readLine()) != null)
                    System.out.println(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("--------------------");
        }
    }


    class ContinuousWriter implements Runnable {


        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    outputStreamWriter.write(String.format("%12s | %s | %s\n", id++, UUID.randomUUID().toString(), this.hashCode()));
                    outputStreamWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                lock.unlock();
                try {
                    Thread.sleep(1113);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
