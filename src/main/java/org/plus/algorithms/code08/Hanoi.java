package org.plus.algorithms.code08;

/**
 * 汉诺塔问题
 * @author mobingsen
 * @date 2021/11/25 23:10
 */
public class Hanoi {

    public static void main(String[] args) {
        int n = 3;
        hanoi(n);
    }

    private static void hanoi(int n) {
        if (n > 0) {
            func(n, "左", "右", "中");
        }
    }

    private static void func(int i, String from, String to, String other) {
        if (i == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            func(i - 1, from, other, to);
            System.out.println("Move " + i + " from " + from + " to " + to);
            func(i - 1, other, to, from);
        }
    }
}
