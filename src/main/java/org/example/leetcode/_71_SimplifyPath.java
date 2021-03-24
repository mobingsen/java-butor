package org.example.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/simplify-path/
 * @author by mbs on 2021/3/24 9:36
 */
public class _71_SimplifyPath {

    public static void main(String[] args) {
        _71_SimplifyPath simplifyPath = new _71_SimplifyPath();
        // /home
        System.out.println(simplifyPath.simplifyPath("/home/"));
        // /
        System.out.println(simplifyPath.simplifyPath("/../"));
        // /home/foo
        System.out.println(simplifyPath.simplifyPath("/home//foo/"));
        // /c
        System.out.println(simplifyPath.simplifyPath("/a/./b/../../c/"));
    }

    public String simplifyPath(String path) {
        if (null == path || path.length() <= 0) {
            return "/";
        }
        Stack<String> stack = new Stack<>();
        for (String s : path.split("/")) {
            if ("".equals(s) || "/".equals(s) || ".".equals(s)) {
                continue;
            }
            if ("..".equals(s)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(s);
            }
        }
        return "/" + String.join("/", stack);
    }
}
