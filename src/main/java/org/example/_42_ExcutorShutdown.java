package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Created by mobingsen on 2021/5/31 0:22
 */
public class _42_ExecutorShutdown {

    private static final ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(), 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    TimeUnit.MINUTES.sleep(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        while (executorService.isTerminated()) {
            executorService.awaitTermination(60, TimeUnit.SECONDS)
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
