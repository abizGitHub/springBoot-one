package abiz.ir.crwlr.some;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagExtractor {


    public static void main(String[] args) throws Exception {
        String s = new String(Files.readAllBytes(Paths.get("main/src/test/sample.html")), StandardCharsets.UTF_8);
        System.out.println(extractTagAttributeValue(s, "a", "href"));
        System.out.println(extractElementValue(s, "a"));
        System.out.println(extractElementValue(s, "div"));
        System.out.println(extractTagAttributeValue(s, "div", "style"));
    }

    public static List<String> extractWholeElement(String html, String tagName) {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("<" + tagName + "[^>]*>[\r\n\t]*.*\\s*</" + tagName + "\\s*>");
        //Pattern pattern = Pattern.compile("<" + tagName + "[^>]*>[\r\n|\r|\n|\t|.]*.*</" + tagName + "\\s*>");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            list.add(matcher.group(0).replaceAll("[\r\n\t]", " ").trim());
        }
        return list;
    }

    public static List<String> extractElementValue(String html, String tagName) {
        List<String> tags = extractWholeElement(html, tagName);
        List<String> list = new ArrayList<>();
        if (tags.isEmpty())
            return null;
        for (String tag : tags) {
            list.add(tag.replaceFirst("<" + tagName + "[^>]*>", "").replaceFirst("</" + tagName + "\\s*>", ""));
        }
        return list;
    }

    public static List<String> extractTagAttributeValue(String html, String tagName, String attrName) {
        List<String> tags = extractWholeElement(html, tagName);
        List<String> list = new ArrayList<>();
        if (tags.isEmpty())
            return null;
        Pattern pattern = Pattern.compile(attrName + "\\s*=\\s*\"[^\"]*");
        for (String tag : tags) {
            Matcher matcher = pattern.matcher(tag);
            if (matcher.find()) list.add(matcher.group(0).replaceFirst(attrName + "\\s*=\\s*\"", ""));
        }
        return list;
    }

    public static List<String> extractLink(String html, boolean justAbs) {
        List<String> tags = extractWholeElement(html, "a");
        List<String> list = new ArrayList<>();
        if (tags.isEmpty())
            return null;
        Pattern pattern = Pattern.compile("href\\s*=\\s*\"[^\"]*");
        for (String tag : tags) {
            Matcher matcher = pattern.matcher(tag);
            if (matcher.find()) {
                String href = matcher.group(0).replaceFirst("href\\s*=\\s*\"", "");
                if (!justAbs || justAbs && href.startsWith("http"))
                    list.add(href);
            }
        }
        return list;
    }

}
