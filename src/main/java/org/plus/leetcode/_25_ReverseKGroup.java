package org.plus.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * @Created by mobingsen on 2021/3/14 14:52
 */
public class _25_ReverseKGroup {

    public static void main(String[] args) {
        _25_ReverseKGroup group = new _25_ReverseKGroup();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode listNode = group.reverseKGroup(head, 2);
        List<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        System.out.println(list);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int index = 0;
        Stack<Integer> stack = new Stack<>();
        ListNode f = null;
        ListNode newH = null;
        while (head != null) {
            index++;
            stack.add(head.val);
            if (index / k > 0 && index % k == 0) {
                while (!stack.isEmpty()) {
                    ListNode p = new ListNode(stack.pop());
                    if (newH == null) {
                        newH = p;
                    }
                    if (f != null) {
                        f.next = p;
                    }
                    f = p;
                }
            }
            head = head.next;
        }
        if (index % k < k) {
            for (Integer integer : stack) {
                ListNode listNode = new ListNode(integer);
                if (f != null) {
                    f.next = listNode;
                }
                f = listNode;
            }
        }
        return newH;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
