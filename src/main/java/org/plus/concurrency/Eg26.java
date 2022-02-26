package org.plus.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by mobingsen on 2020/7/15 17:10
 */
public class Eg26 {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 10, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
        IntStream.range(0, 10).boxed().map(i -> (Callable<Integer>) () -> {
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            System.out.println(Thread.currentThread().getName());
            return i * i;
        }).forEach(completionService::submit);
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = completionService.take();
            System.out.println(future.get());
        }
        executor.shutdown();
    }
}
