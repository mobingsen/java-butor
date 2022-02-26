package org.plus.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by mobingsen on 2020/7/10 11:00
 */
public class Eg19 {

    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> "hello").thenApplyAsync(value -> value + " world").join();
        System.out.println(result);
        System.out.println("===============");
        CompletableFuture.supplyAsync(() -> "hello").thenAcceptAsync(System.out::println);
        System.out.println("===============");
        String result2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result2);
        System.out.println("===============");
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finished..");
        }).whenComplete((v, e) -> System.out.println("task complete"));
        System.out.println("main thread finished");
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
