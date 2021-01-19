package org.example.leetcode;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * Created by mobingsen on 2021/1/12 21:50
 */
public class _5_LongestPalindrome {

    public static void main(String[] args) {
        _5_LongestPalindrome palindrome = new _5_LongestPalindrome();
        System.out.println(palindrome.longestPalindrome("babad"));
        System.out.println(palindrome.longestPalindrome("cbbd"));
        System.out.println(palindrome.longestPalindrome("a"));
        System.out.println(palindrome.longestPalindrome("ac"));
    }

    public String longestPalindrome(String s) {
        int length = s.length();
        if (length == 1) {
            return s;
        }
        if (length == 2) {
            return s.charAt(0) == s.charAt(1) ? s : s.substring(0, 1);
        }
        int len = 1;
        int start = 0;
        boolean[][] dp = new boolean[length][length];
        for (int j = 0; j < length; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else  {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > len) {
                    len = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + len);
    }
}
