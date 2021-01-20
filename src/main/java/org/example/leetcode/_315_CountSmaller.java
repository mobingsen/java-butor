package org.example.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/
 * Created by mobingsen on 2021/1/18 21:19
 */
public class _315_CountSmaller {

    public static void main(String[] args) {
        _315_CountSmaller smaller = new _315_CountSmaller();
        System.out.println(smaller.countSmaller(new int[]{5, 2, 6, 1})); // 2,1,1,0
    }

    private Integer[] c;
    public List<Integer> countSmaller(int[] nums) {
        int[] array = Arrays.stream(nums)
                .distinct()
                .sorted()
                .toArray();
        c = new Integer[nums.length];
        Integer[] result = new Integer[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int pos = Arrays.binarySearch(array, nums[i]);
            result[i] = get(pos);
            update(pos + 1);
        }
        return Arrays.asList(result);
    }

    public int get(int pos) {
        int ret = 0;
        while (pos > 0) {
            ret += (c[pos] == null ? 0 : c[pos]);
            pos -= (pos & (-pos));
        }
        return ret;
    }

    private void update(int pos) {
        while (pos < c.length) {
            c[pos] = (c[pos] == null ? 0 : c[pos]) + 1;
            pos += (pos & (-pos));
        }
    }
}
