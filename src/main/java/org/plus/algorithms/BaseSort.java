package org.plus.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本思想：将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。这样从最低位排序一
 * 直到最高位排序完成以后,数列就变成一个有序序列。
 * Created by mobingsen on 2020/7/20 11:13
 */
public class BaseSort {
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 176, 213, 227, 49, 78, 34, 12, 164, 11, 18, 1};
        System.out.println("排序之前：");
        for (int value : a) {
            System.out.print(value + " ");
        }
        // 基数排序
        sort(a);
        System.out.println();
        System.out.println("排序之后：");
        for (int value : a) {
            System.out.print(value + " ");
        }
    }

    private static void sort(int[] array) {
        // 找到最大数，确定要排序几趟
        int max = 0;
        for (int value : array) {
            if (max < value) {
                max = value;
            }
        }
        // 判断位数
        int times = 0;
        while (max > 0) {
            max = max / 10;
            times++;
        }
        // 建立十个队列
        List<ArrayList<Integer>> queue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> queue1 = new ArrayList<>();
            queue.add(queue1);
        }
        // 进行times次分配和收集
        for (int i = 0; i < times; i++) {
            // 分配
            for (int value : array) {
                int x = value % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(value);
                queue.set(x, queue2);
            }
            // 收集
            int count = 0;
            for (int j = 0; j < 10; j++) {
                while (queue.get(j).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(j);
                    array[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }
    }
}
