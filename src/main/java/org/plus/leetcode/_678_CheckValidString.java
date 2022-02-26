package org.plus.leetcode;

/**
 * https://leetcode-cn.com/problems/valid-parenthesis-string/
 * @author by mobingsen on 2021/9/12 10:53
 */
public class _678_CheckValidString {

    public boolean checkValidString(String s) {
        String[] split = s.split("");
        int index = split.length - 1;
        int x = 0;
        for (int i = 0; i < index; i++) {
            if ("".equals(split[i])) {
                continue;
            }
            if ("(".equals(split[i]) || "*".equals(split[i])) {
                for (int j = index; j > i; j--) {
                    if ("".equals(split[j])) {
                        continue;
                    }
                    if (")".equals(split[i]) || "*".equals(split[i])) {
                        index -= 1;
                        break;
                    }
                }
            }
            if (")".equals(split[i])) {
                return false;
            }
            if ("*".equals(split[i])) {
                x++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _678_CheckValidString string = new _678_CheckValidString();
        System.out.println(string.checkValidString("()"));
        System.out.println(string.checkValidString("(*)"));
        System.out.println(string.checkValidString("(*))"));
    }
}
