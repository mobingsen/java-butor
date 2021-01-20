package org.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by mobingsen on 2020/10/10 11:09
 */
public class _16_BatchNum {

    private static final int BATCH_NUM = 10;

    public static void main(String[] args) {
        List<Integer> list = IntStream.rangeClosed(1, 25).boxed().collect(Collectors.toList());
        add(list);
    }

    public static void add(List<Integer> entities) {
        int len = entities.size();
        if (len <= BATCH_NUM) {
            System.out.println(entities);
        } else {
            int groupCount = len / BATCH_NUM + (len % BATCH_NUM < BATCH_NUM / 2 ? 0 : 1);
            for (int i = 0; i < groupCount; i++) {
                int end = (i + 1) * BATCH_NUM;
                if (i == groupCount - 1) {
                    end = len;
                }
                List<Integer> subList = entities.subList(i * BATCH_NUM, end);
                System.out.println(subList);
            }
        }
    }
}
