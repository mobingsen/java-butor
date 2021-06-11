package org.example.leetcode;

/**
 * https://leetcode-cn.com/problems/network-delay-time/
 *
 * @author mbs on 2021-06-11 10:11
 */
public class _743_NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        return -1;
    }

    public static void main(String[] args) {
        final _743_NetworkDelayTime networkDelayTime = new _743_NetworkDelayTime();
        System.out.println(networkDelayTime.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(networkDelayTime.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 1));
        System.out.println(networkDelayTime.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2));
        System.out.println(networkDelayTime.networkDelayTime(new int[][]{{1, 2, 1}, {2, 1, 1}}, 2, 2));
        System.out.println(networkDelayTime.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 2}}, 3, 1));
    }
}
