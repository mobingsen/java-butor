package org.example.leetcode;

/**
 * https://leetcode-cn.com/problems/predict-the-winner/solution/yu-ce-ying-jia-by-leetcode-solution/
 * @Created by mobingsen on 2021/4/6 22:11
 */
public class _486_PredictTheWinner {

    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        System.arraycopy(nums, 0, dp, 0, len);
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[len - 1] >= 0;
    }
}