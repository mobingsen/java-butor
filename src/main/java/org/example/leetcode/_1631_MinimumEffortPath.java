package org.example.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by mbs on 2020/12/24 11:40
 */
public class _1631_MinimumEffortPath {

    public static void main(String[] args) {
        _1631_MinimumEffortPath path = new _1631_MinimumEffortPath();
        path.minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}});
        path.minimumEffortPath(new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}});
        path.minimumEffortPath(new int[][]{{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}});
    }

    public int minimumEffortPath(int[][] heights) {


        int m = heights.length, n = heights[0].length;
        int[][] dist = new int[m][n];
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[m][n];
        queue.offer(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            for (int[] d : dir) {
                int nx = x + d[0], ny = y + d[1];
                if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                    int effort = Math.max(dist[x][y], Math.abs(heights[x][y] - heights[nx][ny]));
                    dist[nx][ny] = Math.min(dist[nx][ny], effort);
                    queue.offer(new int[]{nx, ny, dist[nx][ny]});
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}
