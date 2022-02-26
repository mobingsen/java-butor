package org.plus.concurrency;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 *
 * 关于CyclicBarrier的底层执行流程
 * 1.初始化CyclicBarrier中的各种成员变量，包括parties、count以及runnable（可选）。
 * 2.当调用await方法时，底层会先检查计数器是否已经归零，如果是的话，那么就首先执行可选的Runnable，接下来开始下一个generation；
 * 3.在下一个分代中，将会重置count值为parties，并且创建新的Generation实例。
 * 4.同时会调用Condition的signalAll方法。唤醒所有在屏障前面等待的线程，让其开始继续执行。
 * 5.如果计数器没有归零，那么当前的调用线程将会通过Condition的await方法，在屏障前进行等待。
 * 6.以上所有执行流程均在lock锁的控制范围内，不会出现并发情况。
 *
 * Created by mobingsen on 2020/7/9 16:47
 */
public class Eg15 {

    public static void main(String[] args) {
        final int LOOP_SIZE = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(LOOP_SIZE, () -> System.out.println("----------"));
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < LOOP_SIZE; i++) {
                new Thread(() -> {
                    try {
                        Thread.sleep((long) (Math.random() * 2000));
                        int rdn = new Random().nextInt(500);
                        System.out.println("hello " + rdn);
                        cyclicBarrier.await();
                        System.out.println("world " + rdn);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
}
