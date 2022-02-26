package org.plus.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 * @Created by mbs on 2020/12/31 11:41
 */
public class _234_IsPalindrome {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, null)));
        _234_IsPalindrome palindrome = new _234_IsPalindrome();
        boolean result = palindrome.isPalindrome(head);
        System.out.println(result);
    }


    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        int num = 0;
        do {
            int val = head.val;
            list.add(val);
            num++;
            head = head.next;
        } while (head != null);
        if (num % 2 != 0 || num == 0) {
            return false;
        }
        for (int i = 0; i < num; i++) {
            if (!list.get(i).equals(list.get(num - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
