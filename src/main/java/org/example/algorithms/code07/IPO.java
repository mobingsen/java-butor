package org.example.algorithms.code07;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author mobingsen
 * @date 2021/11/20 17:16
 */
public class IPO {

    static class Node {
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }

        public int getP() {
            return p;
        }

        public int getC() {
            return c;
        }
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Node> minQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getC));
        PriorityQueue<Node> maxQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getP).reversed());
        for (int i = 0; i < profits.length; i++) {
            minQueue.add(new Node(profits[i], capital[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!minQueue.isEmpty() && minQueue.peek().getC() <= w) {
                maxQueue.add(minQueue.poll());
            }
            if (maxQueue.isEmpty()) {
                return w;
            }
            w += maxQueue.poll().getP();
        }
        return w;
    }
}
