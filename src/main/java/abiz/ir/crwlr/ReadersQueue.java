package abiz.ir.crwlr;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ReadersQueue {

    private PageReader[] readers;
    private int placeIndex = 0;
    private ExecutorService executorService;


    private final Lock lock = new ReentrantLock();
    private final Condition isFull = lock.newCondition();

    public Future<List<String>> addPage(String url) throws Exception {
        lock.lock();
        while (placeIndex >= readers.length)
            isFull.wait();
        readers[placeIndex] = new PageReader(url);
        Future<List<String>> future = executorService.submit(readers[placeIndex]);
        ++placeIndex;
        lock.unlock();
        return future;
    }

    public List<String> getPageLinksList(String url) {
        return null;
    }

    public List<String> getPagesList() {
        return Arrays.stream(readers).map(PageReader::getUrl).collect(Collectors.toList());
    }

    public ReadersQueue(PageReader[] readers) {
        this.readers = readers;
        executorService = Executors.newFixedThreadPool(100);
    }

    public PageReader[] getReaders() {
        return readers;
    }
}
