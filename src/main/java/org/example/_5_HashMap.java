package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap的扩容机制：
 * HashMap默认创建时进行loadFactor的初始化为0.75f；
 * 在添加一个元素时，由于table(Node[])为null，对其进行初resize（），主要计算默认初始化容量为16.默认的阈值为16*0.75=12，然后初始化node
 * 数组长度为16，再赋值给table变量。
 * 添加预算计算key：key的hashcode值&table的长度。
 * 再次扩容时，容量变为16的两倍32，阈值也12的两倍24，table也变为原来的两倍，再逐次把元素移到新的数组中。
 *
 *
 * Created by 小墨 on 2020/9/9 20:35
 */
public class _5_HashMap {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, i);
        }
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
