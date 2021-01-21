package org.example;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mbs on 2021/1/21 15:33
 */
public class _36_For {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("1", "2", "3", "4", "5");
        final int num = stringList.size() - 1;
        for (int i = 0; i < num; i++) {
            System.out.println(stringList.get(i));
        }
    }
}
