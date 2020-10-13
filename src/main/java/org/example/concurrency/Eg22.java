package org.example.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * 关于ReentrantReadWriteLock的操作逻辑：
 * 读锁：
 * 1.在获取读锁时，会尝试判断当前对象是否拥有了写锁，如果已经拥有，则直接失败。
 * 2.如果没有写锁，就表示当前对象没有排它锁，则当前线程会尝试给对象加锁
 * 3.如果当前线程已经持有了该对象的锁，那么直接将读锁数量加1.
 * 写锁：
 * 1.再获取写锁时，会尝试判断当前对象是否拥有了锁（读锁与写锁），如果已经拥有且持有的线程并非当前线程，直接失败。
 * 2.如果当前对象没有被加锁，那么写锁就会为当前对象上锁，并且将写锁的个数加1.
 * 3.将当前对象的排它锁线程持有者设为自己。
 *
 * 关于aqs与synchronized关键字之间的关系：
 * 1.synchronized关键字在底层的c++实现中，存在两个重要的数据结构（集合）：WaitSet，EntryList
 * 2.WaitSet中存放的是调用了object的wait方法的线程对象（被封装成了c++的Node对象）
 * 3.EntryList中存放的是陷入到阻塞状态，需要获取monitor的那些线程对象
 * 4.当一个线程被notify后，它就会从WaitSet中移动到EntryList中。
 * 5.进入到EntryList后，该线程依然需要与其他县城争抢monitor对象
 * 6.如果争抢到，就表示该线程获取到了对象的锁，它就可以以排他方式执行对应的同步代码。
 *
 * 1.AQS中存在两种队列，分别是Condition对象上的条件队列，以及AQS本身的阻塞队列
 * 2.这两个队列中的每一个对象都是Node实例（里面封装了线程对象）
 * 3.当位于Condition条件队列中的线程被其他线程signal后，该线程就会条件队列中移动到aqs的阻塞队列中
 * 4.位于aqs阻塞队列中的Node对象本质上都是由一个双向链表来构成的
 * 5.在获取aqs锁时，这些进入到阻塞队列中的线程会按照在队列中的排序先后尝试获取
 * 6.当aqs阻塞队列中的线程获取到锁后，就表示该线程已经可以正常执行了
 * 7.陷入到阻塞状态的线程，依然需要进入到操作系统的内核态，进入阻塞（park方法实现）
 * Created by 小墨 on 2020/7/10 19:35
 */
public class Eg22 {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void method() {
        readWriteLock.readLock().lock();
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("method...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        Eg22 eg22 = new Eg22();
        IntStream.range(0, 10).forEach(i -> new Thread(eg22::method).start());
    }
}
