package abiz.ir.crwlr;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class QueueManager {

    private ReadersQueue queue;
    private int max;
    private String rootUrl;
    private ConcurrentHashMap<String, Future<List<String>>> map;
    private Checker checker;

    public List<String> getPagesList() {
        return queue.getPagesList();
    }

    public List<String> getPageLinksList(String url) {
        return queue.getPageLinksList(url);
    }

    public QueueManager(int max, String rootUrl) {
        this.max = max;
        this.rootUrl = rootUrl;
        queue = new ReadersQueue(new PageReader[max]);
        map = new ConcurrentHashMap<>();
        checker = new Checker();
    }

    public void start() throws Exception {
        Future<List<String>> future = queue.addPage(rootUrl);
        map.put(rootUrl, future);
        new Thread(checker).start();
    }

    class Checker implements Runnable {

        @Override
        public void run() {
            for (Map.Entry<String, Future<List<String>>> futureEntry : map.entrySet()) {
                Future<List<String>> future = futureEntry.getValue();
                if (future.isDone()) {
                    try {
                        List<String> links = future.get();
                        for (String link : links) {
                            map.put(link, queue.addPage(link));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(".");
        }
    }

}
