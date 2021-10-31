package org.example.algorithms;

/**
 * 递归
 * @author mobingsen
 * @date 2021/10/31 15:12
 */
public class _1_GetMax {

    public static void main(String[] args) {

    }

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            // arr[L...R]范围上只有一个数，直接返回
            return arr[L];
        }
        // 求中点
        int mid = L + ((R- L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }
}
