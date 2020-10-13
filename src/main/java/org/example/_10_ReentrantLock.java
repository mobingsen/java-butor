package org.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock默认使用的是非公平锁实现；
 * 公平锁：即是线程在获取锁时线程排队等待，并不会出现插队情况；
 * 非公平锁：即是线程在获取锁时先插队尝试获取锁，如果失败则加入等待队列
 * ReentrantLock的实现是通过volatile int state、sync和cas共同实现。
 * Created by 小墨 on 2020/9/11 13:53
 */
public class _10_ReentrantLock {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.lock.lock();
                try {
                    while (data.counter == 1) {
                        data.t.await();
                    }
                    data.inc();
                    data.f.signal();
                } catch (Exception ignored) {
                } finally {
                    data.lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.lock.lock();
                try {
                    while (data.counter == 0) {
                        data.f.await();
                    }
                    data.sub();
                    data.t.signal();
                } catch (Exception ignored) {
                } finally {
                    data.lock.unlock();
                }
            }
        }).start();
    }

    @lombok.Data
    static
    class Data {

        private final Lock lock = new ReentrantLock();

        private final Condition t = lock.newCondition();

        private final Condition f = lock.newCondition();

        private int counter;

        public void inc() {
            System.out.println(counter++);
            sleep(100);
        }

        public void sub() {
            System.out.println(counter--);
            sleep(200);
        }

        private void sleep(int len) {
            try {
                TimeUnit.MILLISECONDS.sleep(len);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
