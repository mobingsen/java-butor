package org.example.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * int corePoolSize:核心线程数，线程池中一直维护的线程数量，如果线程池处于任务空闲期间，那么该线程也并不会被回收掉
 * int maximumPoolSize:线程池中所维护的线程数的最大数量
 * long keepAliveTime:超过了corePoolSize的线程在经过keepAliveTime时间后如果一直处理空闲状态，那么超过的这部分线程将会被回收掉
 * TimeUnit unit:指的是keepAliveTime的时间单位
 * BlockingQueue<Runnable> workQueue:向线程池所提交的任务位于的阻塞队列，它的实现有多种方式
 * ThreadFactory threadFactory:线程工厂，用于创建新的线程并被线程池所管理，默认线程工厂所创建的线程都是用户线程且优先级为正常优先级
 * RejectedExecutionHandler handler:拒绝策略，表示阻塞队列已满的情况下，新进来的任务的处理方式
 *      AbortPolicy：放弃任务抛出警告异常
 *      DiscardPolicy：丢弃任务不做任何处理
 *      DiscardOldestPolicy：丢弃掉阻塞队列中存放时间最久的任务（队头元素），并且为当前所提交的任务留出一个队列中的空闲空间，以便将其放
 *                           进到队列中
 *      CallerRunsPolicy：直接由提交任务的线程来运行这个提交的任务
 * Created by mbs on 2020/7/13 17:02
 */
public class Eg23 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.submit(Eg23::doTask);
        }
        executorService.shutdown();
    }

    private static void doTask() {
        Thread thread = Thread.currentThread();
        IntStream.range(0, 50).boxed().map(i -> thread.getName()).forEach(System.out::println);
    }
}
