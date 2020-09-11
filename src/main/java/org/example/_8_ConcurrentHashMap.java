package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mbs on 2020/9/10 10:34
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
