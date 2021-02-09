package abiz.ir.crwlr.some;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer {
    static int port = 8080;

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        char[] chars = new char[124];
        while (reader.read(chars) != -1 && reader.ready()) {
            for (char c : chars) {
                System.out.print(c);
            }
        }
        socket.getOutputStream().write(88);
        Thread.sleep(88);
        socket.getOutputStream().flush();
        socket.getOutputStream().close();
        System.out.println("\n------------");
    }

}
