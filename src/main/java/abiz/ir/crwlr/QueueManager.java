package abiz.ir.crwlr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class QueueManager {

    private ReadersQueue queue;
    private int max;
    private String rootUrl;
    private FutureBox[] boxes;
    private Checker checker;
    private Lock lock;
    private int ix;
    private boolean ignoreRepetitiveUrl;
    public List<String> excludedUrls;

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
        addLink4Check(rootUrl);
        new Thread(checker).start();
    }

    public void addLink4Check(String url) {
        lock.lock();
        Future<List<String>> future = queue.addPage(url);
        boxes[ix++] = new FutureBox(url, future, checker);
        lock.unlock();
    }

    public QueueManager ignoreRepetitiveUrl() {
        ignoreRepetitiveUrl = true;
        return this;
    }

    public QueueManager ignoreUrl(String url) {
        excludedUrls.add(url);
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

        public void boxExcp(Exception e) {

        }

        public void boxDone(FutureBox box) {
            List<String> list = box.getList().stream()
                    .filter(s -> s != box.getRootUrl())
                    .filter(s -> !s.startsWith(box.getRootUrl()))
                    .collect(Collectors.toList());
            if (list != null && !list.isEmpty())
                list.forEach(s -> {
                    System.out.println(s);
                    addLink4Check(s);
                });
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

        public FutureBox(String rootUrl, Future<List<String>> listFuture, Checker checker) {
            this.listFuture = listFuture;
            this.checker = checker;
            this.rootUrl = rootUrl;
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
                checker.boxExcp(e);
            }
            checker.boxDone(this);
        }

        public List<String> getList() {
            return list;
        }

        public String getRootUrl() {
            return rootUrl;
        }
    }


}
