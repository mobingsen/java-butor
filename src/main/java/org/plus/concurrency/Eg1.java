package org.plus.concurrency;

/**
 * 1.查看Thread类源码{@link Thread}, 查看runnable源码{@link Runnable}
 * 2.查看Object类源码{@link Object}
 *      特别注意：wait方法和notify方法以及他们的重载方法。
 *  Object.wait方法会放弃锁(monitor)的所有权，Thread.sleep方法不会放弃锁(monitor)的所有权。
 * 3.反编译源码
 * Microsoft Windows [版本 10.0.17134.1246]
 * (c) 2018 Microsoft Corporation。保留所有权利。
 *
 * D:\workspace-mbs\java-example>cd build\classes\java\main
 *
 * D:\workspace-mbs\java-example\build\classes\java\main>javap -c org.example.concurrency.Eg1
 * Compiled from "Eg1.java"
 * public class org.example.concurrency.Eg1 {
 *   public org.example.concurrency.Eg1();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]) throws java.lang.InterruptedException;
 *     Code:
 *        0: new           #2                  // class java/lang/Object
 *        3: dup
 *        4: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        7: astore_1
 *        8: aload_1
 *        9: invokevirtual #3                  // Method java/lang/Object.wait:()V
 *       12: return
 * }
 *
 * D:\workspace-mbs\java-example\build\classes\java\main>
 *
 * Created by mobingsen on 2020/6/27 11:55
 *
 * 关于wait与notify和notifyAll方法的总结：
 * 1.当调用wait时，首先需要确保调用了wait方法的线程已经持有了对象的锁。
 * 2.当调用wait后，该线程就会释放这个对象的锁，然后进入到等待状态（wait set）。
 * 3.当线程调用了wait后进入到等待状态时，它就可以等待其他线程调用相同对象的notify或notifyAll方法来使得自己被唤醒。
 * 4.一旦这个线程被其他线程唤醒后，该线程就会与其他线程一同开始竞争这个对象的锁（公平竞争）；只有当线程获取到了这个对象的锁后，线程才会继
 *  续往下执行。
 * 5.调用wait方法的代码片段需要放在一个synchronized块或者是synchronized方法中，这样才可以确保线程在调用wait方法前已经获取到了该方法
 *  的锁。
 * 6.当调用对象的notify方法时，它会随机唤醒该对象等待集合（wait set）中的任意一个线程，当某个线程被唤醒后，它就会与其他线程一同竞争对象
 *  的锁。
 * 7.当调用对象的notifyAll方法时，它会唤醒该对象等待集合（wait set）中的所有线程，这些线程被唤醒后，又会开始竞争对象的锁。
 * 8.在某一个时刻，只有一个线程可以拥有对象的锁。
 */
public class Eg1 {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        synchronized (object) {
            object.wait();
        }
    }
}
