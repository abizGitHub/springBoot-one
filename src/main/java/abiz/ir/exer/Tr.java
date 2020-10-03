package abiz.ir.exer;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tr {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Tr tr = new Tr();
        ArrayList list = new ArrayList();
        Condition one = lock.newCondition();
        Condition two = lock.newCondition();
        Condition three = lock.newCondition();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    lock.lock();
                    tr.addOne(list, Thread.currentThread().getName(), one, two, three);
                    lock.unlock();
                    lock.lock();
                    tr.addTwo(list, Thread.currentThread().getName(), one, two, three);
                    lock.unlock();
                    lock.lock();
                    tr.addThree(list, Thread.currentThread().getName(), one, two, three);
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(5000L);
        System.out.println(list);
    }

    private void addOne(ArrayList list, String s, Condition one, Condition two, Condition three) throws InterruptedException {
        one.signal();
        list.add("<1>" + s);
        two.await();
        three.await();
    }

    private void addTwo(ArrayList list, String s, Condition one, Condition two, Condition three) throws InterruptedException {
        two.signal();
        list.add("<2>" + s);
        three.await();
        one.await();
    }

    private void addThree(ArrayList list, String s, Condition one, Condition two, Condition three) throws InterruptedException {
        three.signal();
        list.add("<3>" + s);
        System.out.println(list);
        one.await();
        two.await();
    }


}
