package org.example.concurrency;

/**
 * Created by xiaomo on 2020/6/29 9:51
 */
public class Eg4 {

    public static void main(String[] args) {
        HelloWorld helloWorld1 = new HelloWorld();
        HelloWorld helloWorld2 = new HelloWorld();
        Thread t1 = new Thread(helloWorld1::hello);
        Thread t2 = new Thread(helloWorld2::world);
        t1.start();
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}

class HelloWorld {

    public synchronized void hello() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }

    public synchronized void world() {
        System.out.println("world");
    }
}
