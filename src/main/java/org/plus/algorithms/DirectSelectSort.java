package org.plus.algorithms;

/**
 * 直接选择排序
 * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第
 * 二个数和最后一个数比较为止。
 * Created by mobingsen on 2020/7/2 20:51
 */
public class DirectSelectSort {

    public static void main(String[] args) {
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1, 8 };
        Print.write("排序前：", a);
        for (int i = 0; i < a.length; i++) {
            int min = a[i];
            int n = i; // 最小数的指针
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    n = j;
                }
            }
            a[n] = a[i];
            a[i] = min;
        }
        System.out.println();
        Print.write("排序后：", a);
    }
}
