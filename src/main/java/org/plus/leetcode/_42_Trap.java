package org.plus.leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * @author by mobingsen on 2021/6/24 20:44
 */
public class _42_Trap {

    public int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int maxLeft = 0;
            int maxRight = 0;
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            for (int j = i; j < size; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            final int r = Math.min(maxLeft, maxRight) - height[i];
            System.out.print(r + "\t");
            ans += r;
        }
        return ans;
    }

    public static void main(String[] args) {
        _42_Trap trap = new _42_Trap();
        System.out.println(trap.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap.trap2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    private int lm;
    private int rm;

    public int trap2(int[] height) {
        lm = Arrays.stream(height).limit(2).max().orElse(0);
        rm = Arrays.stream(height).skip(1).max().orElse(0);
        return IntStream.range(1, height.length - 1)
                .boxed()
                .map(i -> getAnInt(height, i))
                .peek(i -> System.out.print(i + "\t"))
                .reduce(0, Integer::sum);
    }

    private int getAnInt(int[] height, Integer i) {
        lm = Math.max(lm, height[i]);
        if (rm == height[i]) {
            rm = Arrays.stream(height).skip(i).max().orElse(0);
        }
        return Math.min(lm, rm) - height[i];
    }
}
