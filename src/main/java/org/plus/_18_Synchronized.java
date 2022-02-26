package org.plus;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.concurrent.CountDownLatch;

/**
 * synchronized
 * 概要
 *      字节码： 0x0020 ACC_SYNCHRONIZED
 *      特性：
 *          原子性     单一线程持有
 *          可见性     内存强制刷新
 *          有序性     as-if-serial/happen-before
 *          重入性     计数器
 *      对象：
 *          对象头（Header）
 *              Mark Word（标记字段）
 *              Klass Point(Class对象指针）
 *              Monitor（EntryList，Owner，WaitSet）
 *          实例数据（Instance Data）
 *          对齐填充（Padding）
 *      锁类型：
 *          001  无锁
 *          101  偏向锁（Biased Locking)
 *          00   轻量级锁
 *          10   重量级锁  膨胀
 *          GC标记
 * 1.对象结构
 *   对象在内存中存储的布局可以分为三块区域：对象头（Header）、实例数据（Instance Data）和对齐填充（Padding）。
 *   「HotSpot虚拟机」markOop.cpp中的C++代码注释片段，描述了64bits下 mark-word 的存储状态，具体可查看网址：
 *      http://hg.openjdk.java.net/jdk8/jdk8/hotspot/file/87ee5ee27509/src/share/vm/oops/markOop.hpp
 *
 *   ·· mark-word：对象标记字段占4个字节，用于存储一些列的标记位，比如：哈希值、轻量级锁的标记位，偏向锁标记位、分代年龄等。
 *   ·· Klass Pointer：Class对象的类型指针，Jdk1.8默认开启指针压缩后为4字节，关闭指针压缩（-XX:-UseCompressedOops）后，长度为8字节。其
 *                    指向的位置是对象对应的Class对象（其对应的元数据对象）的内存地址。
 *   ·· 对象实际数据：包括对象的所有成员变量，大小由各个成员变量决定，比如：byte占1个字节8比特位、int占4个字节32比特位。
 *   ·· 对齐：最后这段空间补全并非必须，仅仅为了起到占位符的作用。由于HotSpot虚拟机的内存管理系统要求对象起始地址必须是8字节的整数倍，所以对象头
 *          正好是8字节的倍数。因此当对象实例数据部分没有对齐的话，就需要通过对齐填充来补全。
 *
 * # Running 64-bit HotSpot VM.
 * # Using compressed oop with 3-bit shift.
 * # Using compressed klass with 3-bit shift.
 * # Objects are 8 bytes aligned.
 * # Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
 * # Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
 *
 * java.lang.Object@722c41f4 十六进制哈希：722c41f4
 * java.lang.Object object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 f4 41 2c (00000001 11110100 01000001 00101100) (742519809)
 *       4     4        (object header)                           72 00 00 00 (01110010 00000000 00000000 00000000) (114)
 *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *   Object对象，总共占16字节
 *   对象头占 12 个字节，其中：mark-word 占 8 字节、Klass Point 占 4 字节
 *   最后 4 字节，用于数据填充找齐
 *
 *   VM Options配置参数 -XX:-UseCompressedOops 关闭指针压缩。
 * # Running 64-bit HotSpot VM.
 * # Objects are 8 bytes aligned.
 * # Field sizes by type: 8, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
 * # Array element sizes: 8, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
 *
 * java.lang.Object@722c41f4 十六进制哈希：722c41f4
 * java.lang.Object object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 f4 41 2c (00000001 11110100 01000001 00101100) (742519809)
 *       4     4        (object header)                           72 00 00 00 (01110010 00000000 00000000 00000000) (114)
 *       8     4        (object header)                           00 1c 8e 1b (00000000 00011100 10001110 00011011) (462298112)
 *      12     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 *   关闭指针压缩后，mark-word 还是占 8 字节不变。
 *   重点在类型指针 Klass Point 的变化，由原来的 4 字节，现在扩增到 8 字节。
 *
 * 对象的哈希值是16进制的，0x722c41f4     --->  72 2c 41 f4
 * 在对象头哈希值存放的结果上看，也有对应的数值。只不过这个结果是倒过来的。
 * 关于这个倒过来的问题是因为，大小端存储导致；
 *
 * Big-Endian：高位字节存放于内存的低地址端，低位字节存放于内存的高地址端
 * Little-Endian：低位字节存放于内存的低地址端，高位字节存放于内存的高地址端
 *
 *
 * 无锁状态，64位虚拟机mark-word结构
 * 未使用25bit+哈希值31bit+未使用1bit+分代年龄4bit+偏向锁标识1bit+锁类型2bit
 * 最右侧的 3 Bit（1 Bit标识偏向锁，2 Bit描述锁的类型）是跟锁类型和GC标记相关的，而 synchronized 的锁优化升级膨胀就是修改的这三位上的标识，
 * 来区分不同的锁类型。从而采取不同的策略来提升性能。
 *
 * Monitor 对象
 * 在HotSpot虚拟机中，monitor是由C++中ObjectMonitor实现。synchronized 的运行机制，就是当 JVM 监测到对象在不同的竞争状况时，会自动切换到
 * 适合的锁实现，这种切换就是锁的升级、降级。Monitor 三种不同的锁实现：偏斜锁（Biased Locking）、轻量级锁和重量级锁。
 * Monitor 主要数据结构参考openJdk的实现：http://hg.openjdk.java.net/jdk8/jdk8/hotspot/file/87ee5ee27509/src/share/vm/runtime/objectMonitor.hpp
 * ObjectMonitor，有两个队列：_WaitSet、_EntryList，用来保存 ObjectWaiter 对象列表。
 * _owner，获取 Monitor 对象的线程进入 _owner 区时，  _count + 1。如果线程调用了 wait() 方法，此时会释放 Monitor 对象， _owner 恢复为
 * 空， _count - 1。同时该等待线程进入 _WaitSet 中，等待被唤醒。每个 Java 对象头中都包括 Monitor 对象(存储的指针的指向)，synchronized也
 * 就是通过这一种方式获取锁，也就解释了为什么 synchronized() 括号里放任何对象都能获得锁🔒！
 *
 * 锁升级过程
 * 网络上找了张图：classpath:image/Java Synchronized原理.png
 *
 * 偏向锁
 * synchronizer源码：http://hg.openjdk.java.net/jdk8/jdk8/hotspot/file/tip/src/share/vm/runtime/synchronizer.cpp
 * UseBiasedLocking 是一个偏向锁检查，1.6之后是默认开启的，1.5中是关闭的，需要手动开启参数是 XX:-UseBiasedLocking=false
 * 偏斜锁会延缓 JIT 预热进程，所以很多性能测试中会显式地关闭偏斜锁，偏斜锁并不适合所有应用场景，撤销操作（revoke）是比较重的行为，只有当存在较多
 * 不会真正竞争的 synchronized 块儿时，才能体现出明显改善。
 *
 * 轻量级锁
 * 当锁是偏向锁的时候，被另一个线程所访问，偏向锁就会升级为轻量级锁，其他线程会通过自旋的形式尝试获取锁，不会阻塞，提高性能。
 * 在代码进入同步块的时候，如果同步对象锁状态为无锁状态（锁标志位为“01”状态，是否为偏向锁为“0”），JVM虚拟机首先将在当前线程的栈帧中建立一个名为锁
 * 记录（Lock Record）的空间，用于存储锁对象目前的Mark Word的拷贝，官方称之为 Displaced Mark Word。
 *
 * 自旋锁
 * 自旋锁是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU。自旋锁的默认
 * 大小是10次，可以调整：-XX：PreBlockSpin。如果自旋n次失败了，就会升级为重量级的锁。
 *
 * 锁会降级吗？
 * Biased lock revocation，当 JVM 进入安全点 SafePoint的时候，会检查是否有闲置的 Monitor，然后试图进行降级。
 * 参考地址：http://blog.ragozin.info/2012/10/safepoints-in-hotspot-jvm.html
 * Created by mobingsen on 2020/10/29 20:07
 */
public class _18_Synchronized {

    public static void main(String[] args) throws InterruptedException {
//        doValidHeader();
        validAtomic(); // 原子性
        validFlag(); // 可见性
        validSerial(); // 有序性
        reLock(); // 可重入性
    }

    private static void doValidHeader() {
        System.out.println(VM.current().details());
        Object obj = new Object();
        System.out.println(obj + " 十六进制哈希：" + Integer.toHexString(obj.hashCode()));
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

    private static volatile int counter = 0;

    private static void validAtomic() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    //synchronized (_18_Synchronized.class) {
                        counter++;
                    //}
                }
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
        System.out.println(counter);
    }

    private static /*volatile*/ boolean flag = false;

    private static void validFlag() {
        new Thread(() -> {
            while (!flag) {
                flag = true;
            }
        }).start();
        new Thread(() -> {
            while (flag) {
                flag = !flag;
            }
        }).start();
    }

    private static void validSerial() {
        Singleton instance = Singleton.instance;
        System.out.println(instance.hashCode());
    }

    static class Singleton {

        private Singleton() {
        }

        private static volatile Singleton instance;

        // 双重检验锁（Double-checked Locking）为什么synchronized 也有可见性的特点，还需要 volatile 关键字？
        // 因为，synchronized 的有序性，不是 volatile 的防止指令重排序。
        // 那如果不加 volatile 关键字可能导致的结果，就是第一个线程在初始化初始化对象，设置 instance 指向内存地址时。第二个线程进入时，有指令
        // 重排。在判断 if (instance == null)  时就会有出错的可能，因为这会可能 instance 可能还没有初始化成功。
        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }

    private static final Object obj = new Object();

    private static void reLock() {
        synchronized (obj) {
            System.out.println("----");
            synchronized (obj) {
                System.out.println("=========");
            }
        }
    }
}
