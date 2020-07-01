package org.example.concurrency;

/**
 * Created by mbs on 2020/6/29 9:43
 */
public class Eg3 {

    public static void main(String[] args) {
        Runnable runnable = new EgRunnable();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }
}

class EgRunnable implements Runnable {

    int x;

    @Override
    public void run() {
        x = 0;
        do {
            System.out.println("result: " + x++);
//            try {
//                Thread.sleep((long) (Math.random() * 1000));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } while (x != 30);
    }
}
