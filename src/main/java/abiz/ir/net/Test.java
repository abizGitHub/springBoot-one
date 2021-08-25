package abiz.ir.net;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    static AtomicInteger integer = new AtomicInteger(10000);

    public static void main(String[] args) throws Exception {
        System.out.println(String.format("%s%06d", "LJ", 124));

        File file = new File("vvv.txt");
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < 999; i++) {
            new Thread(() -> {
                writeInFile(integer, fileWriter);
            }).start();
        }
        Thread.sleep(5555);
        fileWriter.flush();
        fileWriter.close();
    }

    private static void writeInFile(AtomicInteger atomicInteger, FileWriter fileWriter) {
        try {
            fileWriter.append(atomicInteger.getAndDecrement() + "<");
            fileWriter.append("\n");
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(atomicInteger + ">>>>" + e);
        }
    }


}
