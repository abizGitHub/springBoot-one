package abiz.ir.crwlr;

import abiz.ir.exer.TagExtractor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PageReader implements Callable<List<String>> {

    private String url;
    private List<String> links;
    private URLConnection urlConnection;

    public PageReader(String url) {
        this.url = url;
        links = new ArrayList<>();
    }

    @Override
    public List<String> call() throws Exception {
        urlConnection = new URL(url).openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        bufferedReader.lines().forEach(s -> {
            List<String> l = TagExtractor.extractLink(s, true);
            if (l != null && !l.isEmpty()) {
                links.addAll(l);
            }
        });
        System.err.println("SCANNED: "+url);
        return links;
    }

    public String getUrl() {
        return url;
    }

}
