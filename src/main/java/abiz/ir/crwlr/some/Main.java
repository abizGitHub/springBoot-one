package abiz.ir.crwlr.some;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    private static void extractWebPageTitle(String html) {
        Pattern pattern = Pattern.compile("<title>(.*?)</title>");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    private static void extractCss(String html) {
        Pattern pattern = Pattern.compile("<link.*\\s+rel=\"stylesheet\"([^>]+)>");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println("========================================");
        }
    }

    private static void extractJavascripts(String html) {
        Pattern pattern = Pattern.compile("<script.*\\s+type=\"text/javascript\"([^>]+)></script>");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println("========================================");
        }
    }

    private static void extractLinks(String html) {
        Pattern pattern = Pattern.compile("<a href=\"(.*?)\">.+</a>");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println("==========================");
        }
    }

    private static void extractImages(String html) {
        Pattern pattern = Pattern.compile("<img src=\"(.*?)\">");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println("=========================================");
        }
    }

    public static void main(String[] args) {

        try {
            String html = new String(Files.readAllBytes(Paths.get("main/src/test/sample.html")), StandardCharsets.UTF_8);

            System.out.println("Extract Web Page Title");
            extractWebPageTitle(html);

            System.out.println("\nExtract CSS Links");
            extractCss(html);

            System.out.println("\nExtract JavaScript Links");
            extractJavascripts(html);

            System.out.println("\nExtract HTML Links");
            extractLinks(html);

            System.out.println("\nExtract Images");
            extractImages(html);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void timeIr() throws Exception {
        URLConnection urlConnection = new URL("https://www.time.ir/").openConnection();
        InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        String content = bufferedReader.lines().collect(Collectors.joining());
        String s1 = "";
        String quote = "";
        String author = "";
        Matcher matcher = Pattern.compile("<div class=\"randomQuote\">(.*?)</div>", Pattern.MULTILINE).matcher(content);
        if (matcher.find())
            s1 = matcher.group(0).replaceFirst("<div class=\"randomQuote\">", "");
        matcher = Pattern.compile(">(.*?)<", Pattern.MULTILINE).matcher(s1);
        if (matcher.find())
            quote = matcher.group(0).replaceAll(">", "").replaceAll("<", "");
        matcher = Pattern.compile(">([^>]*?)</a>").matcher(s1);
        if (matcher.find())
            author = matcher.group(0).replaceAll("</a>", "").replaceAll(">", "");
        System.out.println(author + " :");
        System.out.println(quote);
    }


}