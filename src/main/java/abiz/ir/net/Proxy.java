package abiz.ir.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Proxy {

    private ServerSocket serverSocket;
    private int port;
    private ExecutorService executorService;

    public static void main(String[] args) throws Exception {
        /*Socket socket = new Socket("127.0.0.1", 9090);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("hi---");
        writer.flush();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }*/
        new Proxy(9090).listen();
    }

    public Proxy(int port) throws Exception {
        this.port = port;
        executorService = Executors.newFixedThreadPool(10);
        serverSocket = new ServerSocket(port);
    }

    public void listen() throws IOException {
        for (; ; ) {
            Socket socket = serverSocket.accept();
            executorService.submit(() -> {
                try {
                    boolean hostExtracted = false;
                    BufferedReader readerClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter writerClient = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader readerOutServer = null;
                    BufferedWriter writerOutServer = null;
                    char[] chars = new char[1024];
                    int readIx = 1;
                    System.out.println(Thread.currentThread() + "-------------- req from client -----------------");
                    while (readerClient.ready() && readIx != -1) {
                        readIx = readerClient.read(chars);
                        for (char c : chars) {
                            System.out.print(c);
                        }
                        if (!hostExtracted) {
                            String host = extractHost(chars);
                            System.err.println("<host>" + host);
                            hostExtracted = true;
                            String[] split = host.split(":");
                            //Socket proxyOutSocket = new Socket(split[0], Integer.valueOf(split[1]));
                            //writerOutServer = new BufferedWriter(new OutputStreamWriter(proxyOutSocket.getOutputStream()));
                            //readerOutServer = new BufferedReader(new InputStreamReader(proxyOutSocket.getInputStream()));
                        }
                        //writerOutServer.write(chars, 0, readIx);
                        //writerOutServer.flush();
                        System.out.println();
                    }
                    //writerOutServer.flush();
                    readIx = 1;
                    System.out.println(Thread.currentThread() + "-------------- resp from out server -----------------");
                   //while (readerOutServer.ready() && readIx != -1) {
                   //    readIx = readerOutServer.read(chars);
                   //    for (char c : chars) {
                   //        System.out.print(c);
                   //    }
                   //    //writerClient.write(chars, 0, readIx);
                   //    //writerClient.flush();
                   //    System.out.println();
                   //}
                    //writerOutServer.close();
                    //readerOutServer.close();
                    //writerClient.close();
                    //readerOutServer.close();
                    socket.close();
                    System.out.println(Thread.currentThread() + "------------- END  -------------");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    private String extractHost(char[] chars) {
        Matcher matcher = Pattern.compile("([\r]?\nhost.*[\r]?\n)", Pattern.CASE_INSENSITIVE).matcher(new String(chars));
        if (matcher.find())
            return matcher.group(0).replaceFirst("(?i)(host)", "").replaceFirst(":", "").trim();
        return null;
    }
}
