package abiz.ir.crwlr.some;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCheck {

    Lock lock;
    Condition c1, c2, c3;
    static int cnt;

    public LockCheck() {
        lock = new ReentrantLock();
        c1 = lock.newCondition();
        c2 = lock.newCondition();
        c3 = lock.newCondition();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        com.nicico.sales.LockCheck lockCheck = new com.nicico.sales.LockCheck();
        for (int i = 0; i < 2; i++) {
            executorService.submit(lockCheck.new Check(lockCheck.c1, lockCheck.lock));
            executorService.submit(lockCheck.new Check(lockCheck.c2, lockCheck.lock));
            executorService.submit(lockCheck.new Check(lockCheck.c3, lockCheck.lock));
           // executorService.submit(lockCheck.new Check(lockCheck.c1, lockCheck.lock));
           // executorService.submit(lockCheck.new Check(lockCheck.c2, lockCheck.lock));
           // executorService.submit(lockCheck.new Check(lockCheck.c3, lockCheck.lock));
        }
        executorService.submit(lockCheck.new Waker(lockCheck.c1, lockCheck.lock));
        executorService.submit(lockCheck.new Waker(lockCheck.c2, lockCheck.lock));
        executorService.submit(lockCheck.new Waker(lockCheck.c3, lockCheck.lock));
        Thread.sleep(1000);
        executorService.shutdownNow();
        System.out.println(cnt/3);
        System.exit(0);
    }


    public class Check implements Callable<String> {
        Lock lock;
        Condition wt;

        public Check(Condition wt, Lock lock) {
            this.wt = wt;
            this.lock = lock;
        }

        @Override
        public String call() throws Exception {
            while (true) {
                lock.lock();
                wt.await();
                lock.unlock();
                // Thread.sleep(133);
            }
        }

    }

    public class Waker implements Runnable {
        Lock lock;
        Condition condition;

        public Waker(Condition condition, Lock lock) {
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                condition.signalAll();
                cnt++;
                lock.unlock();
            }
        }
    }

}
