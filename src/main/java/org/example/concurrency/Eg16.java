package org.example.concurrency;

/**
 * CAS
 * 1.synchronized关键与lock等锁机制都是悲观锁，无论做何种操作，首先都需要先上锁，接下来再去执行后续操作，从而确保了接下来的所有操作都是
 *   由当前这个县城来执行的。
 * 2.乐观锁：线程在操作之前不会做任何预先的处理，而是直接去执行；当在字后执行变量更新的时候，当前线程需要有一种机制来确保当前被操作的变量是
 *   没有被其他线程修改的。CAS是乐观锁的一种极为重要的实现方式、
 *
 * CAS（Compare And Swap）
 * 比较与交换：这是一个不断循环的过程，一直到变量值被成功修改为止。CAS本身是由硬件指令来提供支持的，换句话来说，硬件中是通过一个原子指令来
 * 实现比较与交换的；因此，cas可以确保变量操作的原子性的。
 *
 * javap -v Eg16.class    查看increase方法的字节码
 *          2: getfield      #2                  // Field count:I
 *          5: iconst_1
 *          6: iadd
 *          7: putfield      #2                  // Field count:I
 *
 *
 * Created by 小墨 on 2020/7/9 20:02
 */
public class Eg16 {

    private int count;

    public int getCount() {
        return count;
    }

    /**
     * 读取->修改->写入；这三个操作并非原子操作。
     */
    public void increase() {
        count++;
    }
}
