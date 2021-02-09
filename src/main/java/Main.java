
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
       // int x = 2;
        //for (int ii = 0; ii < 8; ii++) {
            FileReader reader = new FileReader("D:\\sub\\Marco.Polo.S01E10.All.Versions.BluRay.srt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            List<String> list = bufferedReader.lines()
                    .filter(s -> !s.isEmpty() && !s.startsWith("00:"))
                    .filter(
                            s -> {
                                try {
                                    int i = Integer.parseInt(s.trim());
                                    return i % 20 == 0;
                                } catch (NumberFormatException e) {
                                    return true;
                                }
                            }
                    ).collect(Collectors.toList());
            FileWriter fileWriter = new FileWriter("D:\\sub\\QPS01E10.txt");
            list.forEach(s -> {
                try {
                    fileWriter.write(s);
                    fileWriter.write("\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileWriter.close();
            reader.close();
            //x++;
        }
    }


