package org.example.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * Created by modou on 2021/1/12 21:50
 */
public class _5_LongestPalindrome {

    public static void main(String[] args) {
        _5_LongestPalindrome palindrome = new _5_LongestPalindrome();
        System.out.println(palindrome.longestPalindrome(""));
    }

    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        if (s.length() == 2) {
            String[] split = s.split("");
            return split[0].equals(split[1]) ? s : split[0];
        }
        Stack<String> stack = new Stack<>();
        String[] strings = s.split("");
        for (String string : strings) {
            boolean eq = stack.peek().equals(string);

        }
        return null;
    }
}
