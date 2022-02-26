package org.plus;

/**
 * Created by mbs on 2021/1/21 10:30
 */
public class _35_Float {

    public static void main(String[] args) {
        int one = 10;
        int three = 3;
        float result = one * 1.00f / three;
        System.out.println(result);
        System.out.println(Math.round(result * 100) / 100f);
    }
}
