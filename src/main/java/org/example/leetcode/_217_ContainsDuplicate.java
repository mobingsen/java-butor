package org.example.leetcode;

import java.util.Arrays;

public class _217_ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,1};
        System.out.println(Arrays.stream(nums).distinct().count() != nums.length);
    }
}
