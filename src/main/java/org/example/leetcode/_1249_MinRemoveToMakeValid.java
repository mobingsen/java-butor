package org.example.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/minimum-remove-to-make-valid-parentheses/
 * Created by mbs on 2020/12/14 17:00
 */
public class _1249_MinRemoveToMakeValid {

    public static void main(String[] args) {
        _1249_MinRemoveToMakeValid m = new _1249_MinRemoveToMakeValid();
        System.out.println(m.minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(m.minRemoveToMakeValid("a)b(c)d"));
        System.out.println(m.minRemoveToMakeValid("))(("));
        System.out.println(m.minRemoveToMakeValid("(a(b(c)d)"));
    }

    public String minRemoveToMakeValid(String s) {
        final String[] arr = s.split("");
        Stack<Integer> open = new Stack<>();
        Stack<Integer> close = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                if (close.empty()) {
                    open.push(i);
                } else {
                    while (!close.empty()) {
                        arr[close.pop()] = null;
                    }
                }
            } else if (c == ')') {
                if (open.empty()) {
                    arr[i] = null;
                } else {
                    open.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null || open.contains(i) || close.contains(i)) {
                continue;
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}
