package org.example.leetcode;

public class _322_CoinChange {

    public static void main(String[] args) {
        _322_CoinChange c = new _322_CoinChange();
        System.out.println(c.coinChange(new int[]{1, 2, 5}, 11));
    }

    public int coinChange(int[] coins, int amount) {
        int len = amount + 1;
        int[] dp = new int[len];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = len;
            for (int coin : coins) {
                int res = i - coin;
                if (res >= 0 && dp[res] != len) {
                    dp[i] = Math.min(dp[i], 1 + dp[res]);
                }
            }
        }
        return dp[amount] == len ? -1 : dp[amount];
    }
}