package org.example.concurrency;

/**
 * 锁粗化
 * JIT编译器在执行动态编译时，若发现前后相邻的synchronized块使用的是同一个锁对象，那么它就会把这几个synchronized块给合并为一个较大的同
 * 步块，这样做的好处在于线程在执行这些代码时，就无需频繁申请与释放锁了，从而达到申请与释放锁一次，就可以执行完全部的同步代码块，从而提升了
 * 性能。
 * Created by xiaomo on 2020/7/6 11:15
 */
public class Eg8 {

    private Object object = new Object();

    public void method() {
        synchronized (object) {
            System.out.println("11111111");
        }
        synchronized (object) {
            System.out.println("22222222");
        }
        synchronized (object) {
            System.out.println("33333333");
        }
    }
}
