package abiz.ir.net;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mirror {

    private ServerSocket serverSocket;
    private ExecutorService executorService;


    public static void main(String[] args) throws Exception {
        Mirror mirror = new Mirror(9090);
        mirror.justListen();
        //Socket socket = new Socket("www.google.com", 443);
        //BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //writer.write("hi---");
       // writer.flush();
        //String line;
        //while ((line = reader.readLine()) != null) {
          //  System.out.println(line);
        //}
    }

    public Mirror(int port) throws Exception {
        executorService = Executors.newFixedThreadPool(10);
        serverSocket = new ServerSocket(port);
    }

    public void justListen() throws IOException {
        for (; ; ) {
            Socket socket = serverSocket.accept();
            executorService.submit(() -> {
                System.out.println("--------------------  BEGIN ---------------------");
                try {
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    char[] chars = new char[1024];
                    int readIx = 1;
                    while (reader.ready() && readIx != -1) {
                        readIx = reader.read(chars);
                        for (char c : chars) {
                            System.out.print(c);
                        }
                    }
                    inputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("---------------------- END ----------------------");
            });
        }

    }


    public void doMirror() throws IOException {
        for (; ; ) {
            Socket socket = serverSocket.accept();
            executorService.submit(() -> {
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                    char[] chars = new char[1024];
                    int readIx = 1;
                    Thread.sleep(2);
                    while (reader.ready() && readIx != -1) {
                        readIx = reader.read(chars);
                        for (char c : chars) {
                            System.out.print(c);
                        }
                        writer.write(chars, 0, readIx);
                        writer.flush();
                        System.out.println();
                    }
                    writer.flush();
                    writer.close();
                    outputStream.close();
                    inputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }

}
