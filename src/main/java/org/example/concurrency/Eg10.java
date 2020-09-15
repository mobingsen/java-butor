package org.example.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于lock与synchronized关键字在锁的处理上的重要区别
 * 1.锁的获取方式：前者是通过程序代码的方式由开发者收工获取，后者是通过jvm来获取（无需开发者干预）
 * 2.具体实现方式：前者是通过java代码的方式来实现，后者是通过jvm底层来实现（无需开发者关注）
 * 3.锁的释放方式：前者务必通过unlock()方法再finally块中收工释放，后者是通过jvm来释放（无需开发者关注）
 * 4.锁的具体类型：前者提供了多种，如公平锁、非公平锁，后者与前者均提供了可重入锁
 * Created by xiaomo on 2020/7/7 10:47
 */
public class Eg10 {

    private Lock lock = new ReentrantLock(); // 可重入锁

    public void method1() {
        try {
            lock.lock();
            System.out.println("method1 invoked");
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        try {
            lock.lock();
            System.out.println("method2 invoked");
        } finally {
            lock.unlock();
        }
//        boolean bool = false;
//        try {
//            bool = lock.tryLock(800, TimeUnit.MICROSECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (bool) {
//            System.out.println("get the lock");
//            lock.unlock();
//        } else {
//            System.out.println("can't get the lock");
//        }
    }

    public static void main(String[] args) {
        Eg10 eg10 = new Eg10();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                eg10.method1();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                eg10.method2();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
