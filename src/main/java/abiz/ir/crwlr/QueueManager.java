package abiz.ir.crwlr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

public class QueueManager {

    private ReadersQueue queue;
    private int max;
    private String rootUrl;
    private FutureBox[] boxes;
    private Checker checker;
    private Lock lock;
    private long ix;
    private boolean ignoreRepetitiveUrl;
    private boolean ignoreInAnyPartUrl;
    public List<String> excludedUrls;
    private int retry;

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
        boxes = new FutureBox[max];
        checker = new Checker(10, boxes);
        lock = new ReentrantLock();
        excludedUrls = new ArrayList<>();
    }

    public void start() {
        addLink4Check(rootUrl, null);
        new Thread(checker).start();
    }

    public void addLink4Check(String url, Long parentId) {
        lock.lock();
        Logger.log(url, parentId, ix);
        Future<List<String>> future = queue.addPage(url);
        boxes[(int) ix] = new FutureBox(url, future, checker, ix);
        ix++;
        lock.unlock();
    }

    public QueueManager ignoreRepetitiveUrl() {
        ignoreRepetitiveUrl = true;
        return this;
    }

    public QueueManager ignoreInAnyPartUrl() {
        ignoreInAnyPartUrl = true;
        return this;
    }

    public QueueManager ignoreUrl(String url) {
        excludedUrls.add(url);
        return this;
    }

    public QueueManager retry(int retry) {
        this.retry = retry;
        return this;
    }


    class Checker implements Runnable {

        final int maxSize;
        final private FutureBox[] boxes;
        boolean allDone;

        ExecutorService service;

        Checker(int maxSize, FutureBox[] boxes) {
            this.maxSize = maxSize;
            service = Executors.newFixedThreadPool(maxSize);
            this.boxes = boxes;
        }


        @Override
        public void run() {
            do {
                for (FutureBox box : boxes) {
                    if (box != null && !box.isChecked()) {
                        box.setChecked(true);
                        service.submit(box);
                    }
                }
            } while (!allDone);
            service.shutdown();
        }

        public void boxExcp(FutureBox box, Exception e) {
            Logger.log("ERROR in (" + box.getRootUrl() + ") " + e.getMessage(), box.getParentId(), box.parentId);
            System.err.println(e);
        }

        public void boxDone(FutureBox box) {
            Long parentId = box.getParentId();
            for (String childUrl : box.getList()) {
                if (ignoreInAnyPartUrl) {
                    if (excludedUrls.stream().anyMatch(exc -> childUrl.contains(exc))) continue;
                } else {
                    if (excludedUrls.stream().anyMatch(exc -> childUrl.startsWith(Logger.extractBaseUrl(exc))))
                        continue;
                }
                boolean already = LogChecker.isPartlyInLog(Logger.extractBaseUrl(childUrl));
                if (already) continue;
                addLink4Check(childUrl, parentId);
            }
        }

        class UrlFilter implements Predicate<String> {


            @Override
            public boolean test(String s) {
                return false;
            }

            @Override
            public Predicate<String> and(Predicate<? super String> other) {
                return null;
            }

            @Override
            public Predicate<String> negate() {
                return null;
            }

            @Override
            public Predicate<String> or(Predicate<? super String> other) {
                return null;
            }
        }
    }

    class FutureBox implements Runnable {

        private final Future<List<String>> listFuture;
        private boolean checked;
        private final Checker checker;
        private final String rootUrl;
        private List<String> list;
        private final Long parentId;

        public FutureBox(String rootUrl, Future<List<String>> listFuture, Checker checker, Long parentId) {
            this.listFuture = listFuture;
            this.checker = checker;
            this.rootUrl = rootUrl;
            this.parentId = parentId;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        @Override
        public void run() {
            try {
                list = listFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                checker.boxExcp(this, e);
            }
            checker.boxDone(this);
        }

        public List<String> getList() {
            return list;
        }

        public String getRootUrl() {
            return rootUrl;
        }

        public Long getParentId() {
            return parentId;
        }
    }


}
