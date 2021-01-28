package org.example.leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/shortest-path-with-alternating-colors/
 * @Created by mobingsen on 2021/1/28 22:43
 */
public class _1129_ShortestAlternatingPaths {

    public static void main(String[] args) {
        _1129_ShortestAlternatingPaths paths = new _1129_ShortestAlternatingPaths();
        //输入：n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
        //输出：[0,1,-1]
        System.out.println(Arrays.toString(paths.shortestAlternatingPaths(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{})));
    }

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        // 红
        Map<Integer, Set<Integer>> redEdges = Arrays.stream(red_edges)
                .collect(Collectors.groupingBy(array -> array[0], Collectors.mapping(array -> array[1], Collectors.toSet())));
        // 蓝
        Map<Integer, Set<Integer>> blueEdges = Arrays.stream(blue_edges)
                .collect(Collectors.groupingBy(array -> array[0], Collectors.mapping(array -> array[1], Collectors.toSet())));
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {

        }
        return result;
    }
}
