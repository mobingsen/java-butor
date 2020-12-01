package org.example.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class _34_SearchRange {

    public static void main(String[] args) {
        _34_SearchRange range = new _34_SearchRange();
        System.out.println(Arrays.toString(range.searchRange(new int[]{5,7,7,8,8,10}, 8)));
        System.out.println(Arrays.toString(range.searchRange(new int[]{5,7,7,8,8,10}, 6)));
        System.out.println(Arrays.toString(range.searchRange(new int[]{}, 0)));
    }

    public int[] searchRange(int[] nums, int target) {
        int l = -1;
        int r = -1;
        if (nums.length == 0) {
            return new int[] {l, r};
        }
        return find(nums, target, 0, nums.length - 1);
    }

    private int[] find(int[] nums, int target, int start, int end) {
        if (nums.length == 0 || start > end) {
            return new int[] {-1, -1};
        }
        int len = (start + end) / 2;
        int mid = nums[len];
        if (mid > target) {
            return find(nums, target, start, len - 1);
        } else if (mid < target) {
            return find(nums, target, len + 1, end);
        } else {
            int l = start;
            int r = end;
            for (int i = start; i <= len; i++) {
                if (nums[i] != target) {
                    l++;
                }
            }
            for (int i = end; i >= len; i--) {
                if (nums[i] != target) {
                    r--;
                }
            }
            return new int[] {l, r};
        }
    }
}
