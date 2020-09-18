package org.example;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList通过ReentrantLock实现的
 * Created by xiaomo on 2020/9/18 12:01
 */
public class _11_CopyOnWriteArrayList {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }
        System.out.println(arrayList);
    }
}
