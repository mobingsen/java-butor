package org.example.concurrency;

/**
 * volatile关键字
 *
 * private volatile int count;
 *
 * volatile关键字主要有三方面作用：
 * 1.实现long/double类型变量的原子操作，其占据8个字节即是64位，除此之外，其他都是原子操作的。
 * 2.防止指令重排序
 * 3.实现变量的可见性
 *
 * 当时用volatile修饰变量时，应用就不会从寄存器中获取该变量的值，而是从内存（高速缓存）中获取。
 *
 * volatile与锁类似的地方有两点：
 * 1.确保变量的内存可见性
 * 2.防止指令重排序
 *
 * volatile可以确保对变量写操作的原子性，但不具备排他性。另外重要的一点在于使用锁可能会导致线程的上下文切换（内核态与用户态之间的切换），
 * 但是使用volatile并不会出现这种情况
 *
 * 防止指令重排序与实现变量的可见性都是通过一种手段来实现的：内存屏障（memory barrier）
 *
 * 锁同样具备变量内存可见性与防止指令重排序的功能
 * monitorenter
 * 内存屏障（Acquire Barrier，获取屏障）
 * ......
 * 内存屏障（Release Barrier，释放屏障）
 * monitorexit
 *
 * Created by xiaomo on 2020/7/8 16:26
 */
public class Eg12 {
}
