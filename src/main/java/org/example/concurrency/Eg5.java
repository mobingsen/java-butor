package org.example.concurrency;

/**
 * javap -c -v org\example\concurrency\Eg5_1.class
 *
 * jvm的同步是基于进入和退出监视器对象（管程对象）（monitor）来实现的，每个对象实例都会有一个Monitor对象，monitor对象会和java对象一同
 * 创建并销毁。Monitor对象是由C++来实现的。
 *
 * 当多个线程同时访问一段代码时，这些线程会被放到一个EntryList集合中，处于阻塞状态的线程都会被放到该列表当中。接下来，当线程获取到对象的
 * Monitor时，Monitor是依赖于底层操作系统的mutex lock来实现互斥的，线程获取mutex成功，则会持有该mutex，这时其他线程就无法再获取到该
 * mutex。
 *
 * 如果线程调用了wait方法，那么该线程就会释放掉所持有的mutex，并且该线程会进入到WaitSet集合（等待集合）中，等待下一次被其他线程调用notify
 * /notifyAll唤醒。如果当前线程顺利执行完毕方法，那么它也会释放掉所持有的mutex。
 *
 * 总结一下：同步锁在这种实现方法当中，因为Monitor是依赖于底层操作系统实现，这样就存在用户态与内核态之间的切换，所以会增加性能开销。
 *
 * 通过对象互斥锁的概念来保证共享数据操作的完整性。每个对象都对应于一个可称为【互斥锁】的标记，这个标记用于保证在任何时刻，只能有一个线程访
 * 问该对象。
 *
 * 那些处于EntryList与WaitSet中的线程均处于阻塞状态，阻塞操作是由操作系统来完成的，在linux下是通过pthread_mutex_lock函数实现的。线程
 * 被阻塞后便会进入到内核调度状态，这会导致系统在用户态与内核态之间来回切换，严重影响锁的性能。
 *
 * 解决上述问题的办法便是自旋（Spin）。其原理是：当发生对Monitor的争用时，若Owner能够在很短的时间内释放掉锁，则那些正在争用的线程就可以
 * 稍微等待一下（即所谓的自旋），在Owner线程释放锁之后，争用线程可能会立刻获取到锁，从而避免了系统阻塞。不过，当Owner运行的时间超过了临界
 * 值后，争用线程自旋一段时间后依然无法获取到锁，这时争用线程则会停止自旋而进入到阻塞状态。所以总体的思想是：先自旋，不成功再进行阻塞，尽量
 * 降低阻塞的可能性，这对那些执行时间很短的代码块来说有极大的性能提升。显然，自旋在多处理器（多核心）上才有意义。
 *
 * 互斥锁的属性：
 * 1.PTHREAD_MUTEX_TIMED_NP: 这是缺省值，也就是普通锁，当一个线程加锁以后，其余请求锁的线程将会形成一个等待队列，并且在解锁后按照优先级
 *   获取到锁。这种策略可以确保资源分配的公平性。
 * 2.PTHREAD_MUTEX_RECURSIVE_NP：嵌套锁。允许一个线程对同一个锁成功获取多次，并通过unlock解锁。如果是不同线程请求，则在加锁线程解锁时重
 *   新进行竞争。
 * 3.PTHREAD_MUTEX_ERRORCHECK_NP：检错锁。如果一个线程请求同一个锁。则返回EDEADLK，否则与PTHREAD_MUTEX_NP类型动作相同，这样就保证了
 *   当不允许多次加锁时不会出现最简单情况下的死锁。
 * 4.PTHREAD_MUTEX_ADAPTIVE_NP：适应锁，动作最简单的锁类型，仅仅等待解锁后重新竞争。
 *
 * Created by mbs on 2020/6/30 19:36
 */
public class Eg5 {

    /**
     * 只是在方法的标识位上加上了ACC_SYNCHRONIZED关键字
     */
    public static synchronized void method() {
        System.out.println("hello world");
    }
}

class Eg5_1 {

    /**
     * 一个monitorenter
     * 一个monitorexist
     */
    public synchronized void method1() {
        System.out.println("hello world 1");
        throw new RuntimeException();
    }

    /**
     * 一个monitorenter
     * 两个monitorexist
     */
    public synchronized void method2() {
        System.out.println("hello world 2");
    }
}