package abiz.ir.exer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Crwlr {

    ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        Crwlr crwlr = new Crwlr();
        //List<String> links = crwlr.getPageLinks("https://www.projectlombok.org/setup/maven");//"http://balatarin.com/");
        //links.forEach(System.out::println);
        crwlr.doCrowl("https://en.ryte.com/wiki/Crawler#cite_note-1",100);
        crwlr.map.entrySet().stream().forEach(System.out::println);
    }


    public List<String> getPageLinks(String url) throws IOException {
        List list = new ArrayList();
        URLConnection urlConnection = new URL(url).openConnection();
        InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        bufferedReader.lines().forEach(s -> {
            List<String> l = TagExtractor.extractLink(s, true);
            //System.out.println(l);
            if (l != null && !l.isEmpty()) {
                list.addAll(l);
            }
        });
        return list;
    }

    public void doCrowl(String url, int size) throws IOException {
        List<String> pageLinks = getPageLinks(url);
        map.put(url, pageLinks);
        Set<Map.Entry<String, List<String>>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, List<String>>> iterator = entrySet.iterator();
        while (iterator.hasNext() && entrySet.size() < size)
            iterator.next().getValue().forEach(s -> {
                try {
                    putPageLinksInMap(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }

    public void putPageLinksInMap(String url) throws IOException {

        List<String> links = getPageLinks(url);
        if (map.get(url) == null)
            map.put(url, new ArrayList());
        map.get(url).addAll(links);

    }

}
