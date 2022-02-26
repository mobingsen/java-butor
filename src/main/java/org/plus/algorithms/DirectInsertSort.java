package org.plus.algorithms;

/**
 * 1.直接插入排序
 *
 * 基本思想：每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置（从后向前找到合适位置后），直到全部插入排序完为止。
 *
 * 时间复杂度：o(n^2),按照最差的结果来算，比如arr = [7, 6, 5, 4, 3, 2, 1]
 * 空间复杂度：o(1)
 * @author mobingsen
 */
public class DirectInsertSort {

    public static void main(String[] args) {
        int[] arr = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1 };
        Print.write("排序前：", arr);
        solve1(arr);
        solve2(arr);
        Print.write("排序后：", arr);
    }

    private static void solve1(int[] arr) {
        // 直接插入排序 从第二个元素开始i=1
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                // 将大于temp的后移一位
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
    }

    public static void solve2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int j, int i) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
