package org.plus.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by mobingsen on 2020/7/15 16:03
 */
public class Eg25 {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        EgTask egTask = new EgTask(1, 10);
        Integer result = forkJoinPool.invoke(egTask);
        System.out.println(result);
    }
}

class EgTask extends RecursiveTask<Integer> {

    private int limit = 4;
    private int firstIndex;
    private int lastIndex;

    public EgTask(int firstIndex, int lastIndex) {
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        int gap = lastIndex - firstIndex;
        boolean flag = gap <= limit;
        if (flag) {
            System.out.println(Thread.currentThread().getName());
            for (int i = firstIndex; i <= lastIndex; i++) {
                result += i;
            }
        } else {
            int middleIndex = (firstIndex + lastIndex) / 2;
            EgTask left = new EgTask(firstIndex, middleIndex);
            EgTask right = new EgTask(middleIndex + 1, lastIndex);
            invokeAll(left, right);
            result = left.join() + right.join();
        }
        return result;
    }
}
