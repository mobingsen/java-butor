package org.example;

/**
 * disruptor框架就是最好的例子
 * @author mbs on 2021-06-16 8:51
 */
public class _43_CacheLine {

    private static class Padding {
        private volatile long p1, p2, p3, p4, p5, p6, p7; // 56byte
    }

    private static class T extends Padding {
        public volatile long x = 0L; // cache line 64byte
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000_000; i++) {
                arr[0].x = i;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000_000; i++) {
                arr[1].x = i;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
