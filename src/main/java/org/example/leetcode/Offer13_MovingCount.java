package org.example.leetcode;

/**
 * @author by mbs on 2021/3/18 8:42
 */
public class Offer13_MovingCount {

    public static void main(String[] args) {
        Offer13_MovingCount movingCount = new Offer13_MovingCount();
        // 15
        int count1 = movingCount.movingCount(16, 8, 4);
        System.out.println(count1);
        // 21
        int count2 = movingCount.movingCount(14, 14, 5);
        System.out.println(count2);
        // 39
        int count3 = movingCount.movingCount(6, 34, 8);
        System.out.println(count3);
        // 2
        int count4 = movingCount.movingCount(1, 2, 1);
        System.out.println(count4);
        // 135
        int count5 = movingCount.movingCount(38, 15, 9);
        System.out.println(count5);
    }

    public int movingCount(int m, int n, int k) {
        int amount = 0;
        boolean[][] b = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean flag = false;
                if (i == 0 && j == 0) {
                    flag = true;
                }
                // 上
                if (i - 1 >= 0 && !flag) {
                    flag = b[i - 1][j];
                }
                // 下
                if (i + 1 < m && !flag) {
                    flag = b[i + 1][j];
                }
                // 左
                if (j - 1 >= 0 && !flag) {
                    flag = b[i][j - 1];
                }
                // 右
                if (j + 1 < n && !flag) {
                    flag = b[i][j + 1];
                }
                if (sum(i) + sum(j) <= k && flag) {
                    amount++;
                    b[i][j] = true;
                }
                System.out.print(b[i][j] + "\t");
            }
            System.out.println("");
        }
        return amount;
    }

    private int sum(int i) {
        if (i < 10) {
            return i;
        } else if (i < 100) {
            return i / 10 + i % 10;
        } else {
            return 1;
        }
    }
}
