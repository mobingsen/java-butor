package org.example.algorithms.code07;

import java.util.PriorityQueue;

/**
 * @author mobingsen
 * @date 2021/11/20 16:59
 */
public class LessMoneySplitGold {

    public static void main(String[] args) {

    }

    private static int lessMoney(int[] arr) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i : arr) {
            priorityQueue.add(i);
        }
        int sum = 0;
        int cur = 0;
        while (priorityQueue.size() > 1) {
            cur = priorityQueue.poll() + priorityQueue.poll();
            sum += cur;
            priorityQueue.add(cur);
        }
        return sum;
    }
}
