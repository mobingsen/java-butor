package org.example.concurrency;

/**
 * 编写一个多线程程序，实现这样一个目标：
 * 1.存在一个对象，该对象有一个int类型的成员变量counter，该成员变量的初始值为0.
 * 2.创建两个线程，其中一个线程对该对象的成员变量counter增1，另一个线程对该对象的成员变量减1.
 * 3.输出该对象成员变量counter每次变化后的值。
 * 4.最终输出的结果应为：10101010101...
 * Created by mbs on 2020/6/27 14:20
 */
public class Eg2 {

    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(() -> {
            while (true) {
                counter.increase();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                counter.decrease();
            }
        }).start();
    }

    static class Counter {
        private int counter;

        public synchronized void increase() {
            if (counter != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            counter++;
            System.out.println(counter);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify();
        }

        public synchronized void decrease() {
            if (counter != 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            counter--;
            System.out.println(counter);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify();
        }
    }
}
