package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jdk7和8的区别：从JDK1.7版本的ReentrantLock+Segment+HashEntry，到JDK1.8版本中synchronized+CAS+Node+红黑树。
 * >>jdk7: 采用了数组+Segment+分段锁的方式实现。Segment继承了ReentrantLock
 *          size实现：先采用不加锁的方式，连续计算元素的个数，最多计算3次：
 *              1、如果前后两次计算结果相同，则说明计算出来的元素个数是准确的；
 *              2、如果前后两次计算结果都不同，则给每个Segment进行加锁，再计算一次元素的个数；
 *  >>jdk8: 采用Node + CAS + Synchronized + 红黑树来保证并发安全进行实现
 * Created by 小墨 on 2020/9/10 10:34
 */
public class _8_ConcurrentHashMap {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, i);
        }
        System.out.println(map.toString());
    }
}
