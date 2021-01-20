package org.example.algorithms;

/**
 * 希尔排序
 * 基本思想：先取一个小于n的整数d1作为第一个增量，把文件的全部记录分成d1个组。所有距离为d1的倍数的记录放在同一个组中。先在各组内进行直接
 * 插入排序；然后，取第二个增量d2
 * Created by mobingsen on 2020/7/2 15:24
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1 };
        Print.write("排序前：", a);
        int d = a.length;
        do {
            d /= 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < a.length; i++) {
                    int temp = a[i];
                    int j;
                    for (j = i - d; j >= 0 && a[j] > temp; j = j - d) {
                        a[j + d] = a[j];
                    }
                    a[j + d] = temp;
                }
            }
        } while (d != 1);
        System.out.println();
        Print.write("排序后：", a);
    }
}
