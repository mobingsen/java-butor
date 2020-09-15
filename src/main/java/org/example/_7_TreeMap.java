package org.example;

import java.util.Map;
import java.util.TreeMap;

/**
 * TreeMap是对key进行排序的，初始化时可以指定比较器或者key实现Comparable，出入过程中采用红黑树来保证树的平衡。
 * Created by xiaomo on 2020/9/10 10:11
 */
public class _7_TreeMap {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, i);
        }
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
