package org.plus.leetcode;

import java.util.*;

/**
 * @Created by mobingsen on 2021/2/2 21:42
 */
public class _1721_SwapNodes {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(100, 90);
        ListNode h = null;
        ListNode p = null;
        for (int i = 1; i <= list.size(); i++) {
            ListNode n = new ListNode(list.get(i - 1), null);
            if (h == null) {
                h = n;
                p = n;
            } else {
                p.next = n;
                p = n;
            }
        }
        _1721_SwapNodes nodes = new _1721_SwapNodes();
        ListNode listNode = nodes.swapNodes(h, 2);
        List<Integer> result = new ArrayList<>();
        ListNode ln = listNode;
        while (true) {
            result.add(ln.val);
            ln = ln.next;
            if (ln == null) {
                break;
            }
        }
        System.out.println(result);
    }

    public ListNode swapNodes(ListNode head, int k) {
        ListNode temp = head;
        Map<Integer, ListNode> map = new HashMap<>();
        int id = 1;
        map.put(id, head);
        while (head.next != null) {
            head = head.next;
            id++;
            map.put(id, head);
        }
        if (id == 1) {
            return temp;
        }
        if (k <= id) {
            ListNode node1 = map.get(k);
            ListNode node2 = map.get(id - k + 1);
            int val = node1.val;
            node1.val = node2.val;
            node2.val = val;
        }
        return temp;
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
