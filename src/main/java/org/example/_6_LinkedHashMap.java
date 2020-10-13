package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap实际上是对HashMap进行了扩展，最大的区别在于LinkedHashMap对HashMap的newNode方法进行了重写，而newNode中记录了他的前
 * node节点和后node节点，这样就形成了链表结构。
 * Created by 小墨 on 2020/9/10 10:03
 */
public class _6_LinkedHashMap {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, i);
        }
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
