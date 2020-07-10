package org.example.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by mbs on 2020/7/10 10:36
 */
public class Eg18 {

    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            System.out.println("pre execution");
            Thread.sleep(5000);
            int rdnNumber = new Random().nextInt(500);
            System.out.println("post execution");
            return rdnNumber;
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println("thread has started");
        try {
            Thread.sleep(2000);
            System.out.println(futureTask.get(1, TimeUnit.MICROSECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
