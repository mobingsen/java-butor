package org.plus.concurrency;

/**
 * 编写一个多线程程序，实现这样一个目标：
 * 1.存在一个对象，该对象有一个int类型的成员变量counter，该成员变量的初始值为0.
 * 2.创建两个线程，其中一个线程对该对象的成员变量counter增1，另一个线程对该对象的成员变量减1.
 * 3.输出该对象成员变量counter每次变化后的值。
 * 4.最终输出的结果应为：10101010101...
 * Created by mobingsen on 2020/6/27 14:20
 */
public class Eg2 {

    public static void main(String[] args) {
        Counter counter = new Counter();
        new IncreaseThread(counter).start();
        new DecreaseThread(counter).start();
        // 直接运行以上的increase1和decrease1线程，结果是正确的的，但是如果分别对increase1和decrease1线程再增加多个线程进行处理就会
        // 出现了意料之外的错误这是因为condition的条件判断使用了if，而在object的wait方法中说明使用while。
        // 同一个condition时存在多个线程并发时，必须使用notifyAll才行，使用notify方法会导致满足条件的线程无法唤醒。
        new IncreaseThread(counter).start();
        new DecreaseThread(counter).start();
    }
}

class IncreaseThread extends Thread {

    private final Counter counter;

    IncreaseThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            counter.increase();
        }
    }
}

class DecreaseThread extends Thread {

    private final Counter counter;

    DecreaseThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            counter.decrease();
        }
    }
}

class Counter {

    private int counter;

    public synchronized void increase() {
//            if (counter != 0) {
        while (counter != 0) {
            try {
                System.out.println("--" + counter);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter++;
        System.out.println(counter);
        notifyAll();
    }

    public synchronized void decrease() {
//            if (counter != 1) {
        while (counter != 1) {
            try {
                System.out.println("--" + counter);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter--;
        System.out.println(counter);
        notifyAll();
    }
}
