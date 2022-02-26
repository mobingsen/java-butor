package org.plus.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 * @author by mbs on 2021/3/12 8:48
 */
public class _239_MaxSlidingWindow {

    public static void main(String[] args) {
        _239_MaxSlidingWindow window = new _239_MaxSlidingWindow();
        int[] ints = window.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(window.maxSlidingWindow(new int[]{1}, 1)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = Math.max(nums.length - k, 0);
        int[] result = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            Integer temp = null;
            for (int j = 0; j < k; j++) {
                if ((temp == null || nums[i + j] > temp) && i + j < nums.length) {
                    temp = nums[i + j];
                }
            }
            if (temp == null) {
                continue;
            }
            result[i] = temp;
        }
        return result;
    }
}
