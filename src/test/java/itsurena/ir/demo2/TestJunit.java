package itsurena.ir.demo2;


import com.fasterxml.jackson.databind.util.ClassUtil;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.*;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJunit {

    @Test
    public void w() {
        assertEquals(1, 1);
    }

    @Test
    public void a() {
        assertEquals(1, 1);
        try {
            System.out.println(1 / 0);
            fail("falied");
        } catch (Exception e) {
            assertTrue(e instanceof ArithmeticException);
            //assertTrue(e instanceof ChangeSetPersister.NotFoundException);
        }
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void b() {
        exception.expect(ArithmeticException.class);
        exception.expectMessage("/ by zero");
        int x = 1 / 0;
        fail();
        x *= 0;
    }


    @Test
    public void t() throws Exception {
        String lock = UUID.randomUUID().toString();
        new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("locked");
                    lock.wait();
                    System.out.println("locking end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        HashMap hashMap = new HashMap();
        new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(4444);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("notify");
                lock.notify();
            }
        }).start();

        for (; ; ) {
            hashMap.put(lock, "lockedMsg");
            //System.out.println(hashMap.get(lock));
            hashMap.remove(lock);
        }
    }


    @Test
    public void t2() throws Exception {
        List<Thread> list = new ArrayList<>();
        System.out.println("1---------------");
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                System.out.println("start-" + finalI);
                try {
                    Thread.sleep(1700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("stop-" + finalI);
            });
            thread.start();
            list.add(thread);
        }
        System.out.println("2---------------");
        for (Thread thread : list) {
            thread.join();
        }
        System.out.println("3---------------");
    }

    @Test
    public void q1() {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                for (; ; ) {
                    queue.add(finalI);
                    try {
                        Thread.sleep(1001);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                for (; ; )
                    try {
                        System.out.println(finalI + "-trying to get ...");
                        System.out.println(finalI + "-I've to got! this:" + queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }).start();
        }
        try {
            Thread.sleep(9999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
