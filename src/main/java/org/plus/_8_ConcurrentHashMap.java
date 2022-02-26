package org.plus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jdk7和8的区别：从JDK1.7版本的ReentrantLock+Segment+HashEntry，到JDK1.8版本中synchronized+CAS+Node+红黑树。
 * >>jdk7: 采用了数组+Segment+分段锁的方式实现。Segment继承了ReentrantLock
 *          size实现：先采用不加锁的方式，连续计算元素的个数，最多计算3次：
 *              1、如果前后两次计算结果相同，则说明计算出来的元素个数是准确的；
 *              2、如果前后两次计算结果都不同，则给每个Segment进行加锁，再计算一次元素的个数；
 *  >>jdk8: 采用Node + CAS + Synchronized + 红黑树来保证并发安全进行实现
 * Created by mobingsen on 2020/9/10 10:34
 */
public class _8_ConcurrentHashMap {

    public static void main(String[] args) {
        Map<Node, Integer> map = new ConcurrentHashMap<>(16);
        for (int i = 0; i < 10; i++) {
            // 数组+链表|红黑树    当元素插入数组时是用cas操作的，操作链表时是synchronized代码块的，
            map.put(new Node(i), i);
        }
        System.out.println(map.toString());
    }

    static class Node {
        private int i;

        public Node(int i) {
            this.i = i;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        @Override
        public int hashCode() {
            return i % 3;
        }

        @Override
        public boolean equals(Object obj) {
            if (null == obj) return false;
            if (!(obj instanceof Node)) return false;
            Node node = (Node) obj;
            return node.getI() == this.getI();
        }
    }
}
