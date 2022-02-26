package org.plus.leetcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode-cn.com/problems/course-schedule/
 */
public class _207_CanFinish {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = Arrays.stream(prerequisites)
                .collect(Collectors.groupingBy(k -> k[1], Collectors.mapping(v -> v[0], Collectors.toList())));
        Map<Integer, Integer> over = new HashMap<>(numCourses);
        AtomicBoolean p = new AtomicBoolean(true);
        boolean match = IntStream.range(0, numCourses).anyMatch(i -> {
            if (over.getOrDefault(i, 0) == 0) {
                dfs(i, map, over, p);
            }
            return !p.get();
        });
        return !match;
    }

    public void dfs(int u, Map<Integer, List<Integer>> map, Map<Integer, Integer> over, AtomicBoolean p) {
        over.putIfAbsent(u, 1);
        List<Integer> list = map.getOrDefault(u, Collections.emptyList());
        for (int v: list) {
            Integer or = over.getOrDefault(v, 0);
            if (or == 0) {
                dfs(v, map, over, p);
                if (!p.get()) {
                    return;
                }
            } else if (or == 1) {
                p.compareAndSet(true, false);
                return;
            }
        }
        over.put(u, 2);
    }

    public static void main(String[] args) {
        _207_CanFinish finish = new _207_CanFinish();
        System.out.println(finish.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }
}