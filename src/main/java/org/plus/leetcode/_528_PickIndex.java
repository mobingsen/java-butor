package org.plus.leetcode;

import java.util.Random;

/**
 * https://leetcode-cn.com/problems/random-pick-with-weight/
 * @author by mobingsen on 2021/8/31 18:48
 */
public class _528_PickIndex {

    private final int[] tem;
    private int total;

    public _528_PickIndex(int[] w) {
        this.tem = w;
        for (int i : w) {
            this.total += i;
        }
    }

    public int pickIndex() {
        int i = new Random().nextInt(total);
        int t = 0;
        for (int j = 0; j < tem.length; j++) {
            if (i <= t) {
                return j;
            }
            t += tem[j];
        }
        return 0;
    }
}
