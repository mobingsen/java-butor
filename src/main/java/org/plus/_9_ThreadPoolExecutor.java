package org.plus;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by mobingsen on 2020/9/10 20:44
 */
public class _9_ThreadPoolExecutor {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                2,
                30L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 20; i++) {
            final int id = i;
            executor.submit(() -> {
                // 这样的业务逻辑要做异常捕获，否则业务异常会导致当前线程退出线程池，引起线程的创建和销毁开销。
                try {
                    System.out.println(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.awaitTermination(1000L, TimeUnit.SECONDS);
    }
}
