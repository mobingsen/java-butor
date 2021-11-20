package org.example.algorithms.code08;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列，也不在同一条斜线上。
 * 给定一个整数n，返回n皇后的摆法有多少种。
 * n = 1， 返回 1；
 * n = 2 或者 3， 2皇后和3皇后问题无论怎么摆都不行，返回0；
 * n = 8， 返回92；
 * @author mobingsen
 * @date 2021/11/20 17:36
 */
public class NQueens {

    public static void main(String[] args) {
        int n = 31;
        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start));

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start));
    }

    /**
     * 请不要超过32皇后问题
     * @param n
     * @return
     */
    private static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     *
     * @param limit
     * @param colLim 列的限制，1的位置不能放皇后，0的位置可以
     * @param leftDiaLim 左斜线的限制，1的位置不能放皇后，0的位置可以
     * @param rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
     * @return
     */
    private static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        int pos = 0;
        int mostRightOne = 0;
        pos = limit & (~ (colLim | leftDiaLim | rightDiaLim));
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        // record[i] -> i行的皇后放在了第几列
        int[] record = new int[n];
        return process1(0, record, n);
    }

    private static int process1(int i, int[] record, int n) {
        // 终止行
        if (i== n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            // 当前i行的皇后放在j列会不会和之前（0~i-1）的皇后共行共列或者共斜线
            // 如果是，认为无效，如果不是认为有效
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }
}
