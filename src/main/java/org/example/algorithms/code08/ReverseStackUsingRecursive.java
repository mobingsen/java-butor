package org.example.algorithms.code08;

import java.util.Stack;

/**
 * @author mobingsen
 * @date 2021/11/28 22:46
 */
public class ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    private static int f(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        }
        else {
            int n = f(stack);
            stack.push(pop);
            return n;
        }
    }
}
