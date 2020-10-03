package abiz.ir.exer;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Dlr {

    String dir = "E:\\pdfs\\cncr\\";

    void dnl(List<String> urls) throws Exception {
        for (String url : urls) {
            String fileName = url.substring(url.lastIndexOf("/"));
            HttpURLConnection httpsURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            InputStream inputStream = httpsURLConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(dir + fileName);
            byte[] bytes = new byte[2048*50];
            while (inputStream.read(bytes) != -1) {
                fileOutputStream.write(bytes);
            }
            inputStream.close();
            fileOutputStream.close();
        }
    }

    public static void main(String[] args) throws Exception {
        List<String> list = Arrays.asList("https://cloud.amoozesh365.ir/video/v28/amoozesh-linux-part2/amoozesh-linux-part2-1.mp4",
                "https://cloud.amoozesh365.ir/video/v28/amoozesh-linux-part2/amoozesh-linux-part2-2.mp4");
        new Dlr().dnl(list);
    }


}
