package org.example;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mbs on 2020/9/9 20:29
 */
public class _4_LinkedList {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        for (Integer e : list) {
            System.out.println(e);
        }
    }
}
