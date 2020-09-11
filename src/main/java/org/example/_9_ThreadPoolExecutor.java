package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by mbs on 2020/9/10 20:44
 */
public class _9_ThreadPoolExecutor {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 30L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 20; i++) {
            final int id = i;
            executor.submit(() -> System.out.println(id));
        }
        executor.awaitTermination(1000L, TimeUnit.SECONDS);
    }
}
