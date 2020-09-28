package org.example;

import java.util.Arrays;

/**
 *
 * Created by xiaomo on 2020/9/28 11:37
 */
public class _13_Arrays {

    public static void main(String[] args) {
        int[] nums = { 2, 8, 5, 3, 9, 1};
        Arrays.sort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
