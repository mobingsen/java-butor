package org.example.leetcode;

/**
 * https://leetcode-cn.com/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode-solution/
 * @author mobingsen
 */
public class _402_RemoveKdigits {

    public String removeKdigits(String num, int k) {
        if (num.length() == k) {
            return "0";
        }
        return "";
    }
}
