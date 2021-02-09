package abiz.ir.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class PortListener {

    public static void main(String[] args) throws Exception {
        connectSocket();
        //httpConnection();
    }

    private static void httpConnection() throws Exception {
        URLConnection urlConnection = new URL("https://www.edx.org/").openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    private static void connectSocket() throws Exception {
        InetAddress[] all = InetAddress.getAllByName("www.google.com");
        Socket socket = new Socket(all[0].getHostAddress(), 80);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("GET HTTP/1.1\r\n");
        writer.write("Host: www.google.com\r\n");
        writer.write("Connection: keep-alive\r\n");
        writer.write("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36\r\n");
        writer.flush();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        socket.close();
    }

}
