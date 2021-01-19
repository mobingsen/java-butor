package org.example.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * CountDownLatch
 *
 * Created by mobingsen on 2020/7/9 15:30
 */
public class Eg14 {

    public static void main(String[] args) {
        final int num = 3;
        CountDownLatch latch = new CountDownLatch(num);
        IntStream.rangeClosed(1, num).boxed().map(i -> new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        })).forEach(Thread::start);
        System.out.println("任务执行...");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完毕...");
    }
}
