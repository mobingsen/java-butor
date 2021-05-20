package org.example.leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode-cn.com/problems/course-schedule/
 */
public class _207_CanFinish {

    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = Arrays.stream(prerequisites)
                .collect(Collectors.groupingBy(k -> k[1], Collectors.mapping(v -> v[0], Collectors.toList())));
        Map<Integer, Integer> over = new HashMap<>(numCourses);
        IntStream.range(0, numCourses).anyMatch(i -> {
            if (over.getOrDefault(i, 0) == 0) {
                dfs(i, map, over);
            }
            return !valid;
        });
        return valid;
    }

    public void dfs(int u, Map<Integer, List<Integer>> map, Map<Integer, Integer> over) {
        over.putIfAbsent(u, 1);
        List<Integer> list = map.getOrDefault(u, Collections.emptyList());
        for (int v: list) {
            Integer or = over.getOrDefault(v, 0);
            if (or == 0) {
                dfs(v, map, over);
                if (!valid) {
                    return;
                }
            } else if (or == 1) {
                valid = false;
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