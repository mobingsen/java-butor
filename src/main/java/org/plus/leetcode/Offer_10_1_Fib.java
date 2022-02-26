package org.plus.leetcode;

/**
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 * @author by mobingsen on 2021/9/5 0:00
 */
public class Offer_10_1_Fib {

    public int fib(int n) {
        final int MOD = 1000000007;
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; ++i) {
            p = q;
            q = r;
            r = (p + q) % MOD;
        }
        return r;
    }

    public static void main(String[] args) {
        Offer_10_1_Fib fib = new Offer_10_1_Fib();
        System.out.println(fib.fib(95));
    }
}
