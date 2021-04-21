package org.example.leetcode;

/**
 * https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 * @author by mbs on 2021/3/24 9:59
 */
public class _1190_ReverseParentheses {

    public static void main(String[] args) {
        _1190_ReverseParentheses parentheses = new _1190_ReverseParentheses();
        //输入：s = "(abcd)"
        //输出："dcba"
        System.out.println(parentheses.reverseParentheses("(abcd)"));
        //输入：s = "(u(love)i)"
        //输出："iloveu"
        System.out.println(parentheses.reverseParentheses("(u(love)i)"));
        //输入：s = "(ed(et(oc))el)"
        //输出："leetcode"
        System.out.println(parentheses.reverseParentheses("(ed(et(oc))el)"));
        //输入：s = "a(bcdefghijkl(mno)p)q"
        //输出："apmnolkjihgfedcbq"
        System.out.println(parentheses.reverseParentheses("a(bcdefghijkl(mno)p)q"));
        System.out.println(parentheses.reverseParentheses("ta()usw((((a))))"));
    }

    public String reverseParentheses(String s) {
        if (null == s || s.length() < 1) {
            return s;
        }
        if ("(".equals(s) || ")".equals(s) || "()".equals(s)) {
            return "";
        }
        int i = -1;
        while ((i = s.lastIndexOf("(")) != -1) {
            StringBuilder sb = new StringBuilder();
            if (i > 0) {
                sb.append(s, 0, i);
            }
            int j = s.indexOf(")", i);
            if (j != -1) {
                StringBuilder builder = new StringBuilder();
                for (int p = j - 1; p >= i + 1; p--) {
                    char c = s.charAt(p);
                    if (c == ')') {
                        builder.append("(");
                    } else if (c == '(') {
                        builder.append(")");
                    } else {
                        builder.append(c);
                    }
                }
                sb.append(builder);
            }
            if (j + 1 < s.length()) {
                sb.append(s, j + 1, s.length());
            }
            s = sb.toString();
        }
        return s;
    }
}
