package org.example.jvm;

/**
 * 垃圾回收   引用计数法与可达性分析:
 * 引用计数法无法处理循环引用对象。
 * 目前 Java 虚拟机的主流垃圾回收器采取的是可达性分析算法。这个算法的实质在于将一系列 GC Roots 作为初始的存活对象合集（live set），然后
 * 从该合集出发，探索所有能够被该集合引用到的对象，并将其加入到该集合中，这个过程我们也称之为标记（mark）。最终，未被探索到的对象便是死亡
 * 的，是可以回收的。
 *
 * 可以暂时理解为由堆外指向堆内的引用，一般而言，GC Roots 包括（但不限于）如下几种：
 * 1.Java 方法栈桢中的局部变量；
 * 2.已加载类的静态变量；
 * 3.JNI handles；
 * 4.已启动且未停止的 Java 线程。
 *
 * Stop-the-world 以及安全点
 * Java 虚拟机中的 Stop-the-world 是通过安全点（safepoint）机制来实现的。当 Java 虚拟机收到 Stop-the-world 请求，它便会等待所有的线
 * 程都到达安全点，才允许请求 Stop-the-world 的线程进行独占的工作。
 * 回收死亡对象的内存共有三种方式，分别为：会造成内存碎片的清除、性能开销较大的压缩、以及堆使用效率较低的复制。
 *
 * Java 虚拟机将堆划分为新生代和老年代。其中，新生代又被划分为 Eden 区，以及两个大小相同的 Survivor 区。
 * 默认情况下，Java 虚拟机采取的是一种动态分配的策略（对应 Java 虚拟机参数 -XX:+UsePSAdaptiveSurvivorSizePolicy），根据生成对象的速
 * 率，以及 Survivor 区的使用情况动态调整 Eden 区和 Survivor 区的比例。
 * 当然，你也可以通过参数 -XX:SurvivorRatio 来固定这个比例。但是需要注意的是，其中一个 Survivor 区会一直为空，因此比例越低浪费的堆空间
 * 将越高。
 * TLAB（Thread Local Allocation Buffer，对应虚拟机参数 -XX:+UseTLAB，默认开启）。
 *
 * 如果一个对象被复制的次数为 15（对应虚拟机参数 -XX:+MaxTenuringThreshold），那么该对象将被晋升（promote）至老年代。另外，如果单个
 * Survivor 区已经被占用了 50%（对应虚拟机参数 -XX:TargetSurvivorRatio），那么较高复制次数的对象也会被晋升至老年代。
 *
 * 卡表-这个技术是用于解决减少老年代的全堆空间扫描
 *
 * 针对新生代的垃圾回收器共有三个：Serial，Parallel Scavenge 和 Parallel New。这三个采用的都是标记 - 复制算法。其中，Serial 是一个单
 * 线程的，Parallel New 可以看成 Serial 的多线程版本。Parallel Scavenge 和 Parallel New 类似，但更加注重吞吐率。此外，Parallel
 * Scavenge 不能与 CMS 一起使用。
 *
 * 针对老年代的垃圾回收器也有三个：刚刚提到的 Serial Old 和 Parallel Old，以及 CMS。Serial Old 和 Parallel Old 都是标记 - 压缩算法。
 * 同样，前者是单线程的，而后者可以看成前者的多线程版本。
 *
 * CMS 采用的是标记 - 清除算法，并且是并发的。除了少数几个操作需要 Stop-the-world 之外，它可以在应用程序运行过程中进行垃圾回收。在并发收
 * 集失败的情况下，Java 虚拟机会使用其他两个压缩型垃圾回收器进行一次垃圾回收。由于 G1 的出现，CMS 在 Java 9 中已被废弃 [3]。
 *
 * G1（Garbage First）是一个横跨新生代和老年代的垃圾回收器。实际上，它已经打乱了前面所说的堆结构，直接将堆分成极其多个区域。每个区域都可
 * 以充当 Eden 区、Survivor 区或者老年代中的一个。它采用的是标记 - 压缩算法，而且和 CMS 一样都能够在应用程序运行过程中并发地进行垃圾回收。
 *
 * G1 能够针对每个细分的区域来进行垃圾回收。在选择进行垃圾回收的区域时，它会优先回收死亡对象较多的区域。这也是 G1 名字的由来。
 *
 * 即将到来的 Java 11 引入了 ZGC，宣称暂停时间不超过 10ms。
 *
 * Created by xiaomo on 2020/8/5 19:45
 */
public class _9_Gc {
}
