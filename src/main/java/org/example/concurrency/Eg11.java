package org.example.concurrency;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 传统上，我们可以通过synchronized关键字+wait+notify/notifyAll来实现多个线程之间的协调与通信，整个过程都是由jvm来帮助我们实现的；开
 * 发者无需（也是无法）了解底层的实现细节。
 *
 * 从jdk1.5开始，并发包提供了lock，condition（wait与signal/signalAll）来实现多个线程之间的协调与通信，整个过程都是由开发者来控制的，
 * 而且相比于传统方式，更加灵活，功能也更加强大。
 *
 * Thread.sleep与await（或是object的wait方法）的本质区别：sleep方法本质上不会释放锁，而await会释放锁，并且在signal后，还需要重新获得
 * 锁才能继续执行（该行为与object的wait方法完全一致）
 * Created by 小墨 on 2020/7/7 16:00
 */
public class Eg11 {

    public static void main(String[] args) throws InterruptedException {
        BoundedContainer container = new BoundedContainer();
        IntStream.range(0, 20).boxed().map(String::valueOf)
                .map(element -> new Thread(() -> container.put(element))).forEach(Thread::start);
        IntStream.range(0, 20).boxed().map(i -> new Thread(container::take)).forEach(Thread::start);
    }
}

class BoundedContainer {

    private String[] elements = new String[10];

    private Lock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();

    private Condition notFull = lock.newCondition();

    private int elementCount;

    private int putIndex;

    private int takeIndex;

    public void put(String element) {
        lock.lock();
        try {
            while (elementCount == elements.length) {
                notFull.await();
            }
            elements[putIndex] = element;
            elementCount++;
            putIndex++;
            putIndex %= elements.length;
            System.out.println("put element: " + Arrays.toString(elements));
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String take() {
        lock.lock();
        try {
            while (elementCount == 0) {
                notEmpty.await();
            }
            String element = elements[takeIndex];
            elements[takeIndex] = null;
            elementCount--;
            takeIndex++;
            takeIndex %= elements.length;
            System.out.println("take element: " + Arrays.toString(elements));
            notFull.signalAll();
            return element;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }
}
