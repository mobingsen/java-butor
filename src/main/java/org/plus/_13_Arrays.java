package org.plus;

import java.util.Arrays;

/**
 *
 * Created by mobingsen on 2020/9/28 11:37
 */
public class _13_Arrays {

    public static void main(String[] args) {
        int[] nums = { 2, 8, 5, 3, 9, 1};
        Arrays.sort(nums);// 数字排序是快排，对象则是MergeSort和TimSort
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
