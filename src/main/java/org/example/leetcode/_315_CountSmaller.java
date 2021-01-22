package org.example.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/
 * Created by mobingsen on 2021/1/18 21:19
 */
public class _315_CountSmaller {

    private Integer[] c;

    public List<Integer> countSmaller(int[] nums) {
        // 去重从小到大排序
        int[] array = Arrays.stream(nums)
                .distinct()
                .sorted()
                .toArray();
        // 计算数组
        c = new Integer[nums.length];
        // 统计结果
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
            // https://www.cnblogs.com/xenny/p/9739600.html
            pos -= pos & (pos - 1);
        }
        return ret;
    }

    private void update(int pos) {
        while (pos < c.length) {
            c[pos] = (c[pos] == null ? 0 : c[pos]) + 1;
            pos += pos & (pos - 1);
        }
    }
}
