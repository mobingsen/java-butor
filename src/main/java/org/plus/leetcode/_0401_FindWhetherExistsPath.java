package org.plus.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/route-between-nodes-lcci/
 *
 * @Created by mobingsen on 2021/1/28 21:14
 */
public class _0401_FindWhetherExistsPath {

    public static void main(String[] args) {
        _0401_FindWhetherExistsPath paths = new _0401_FindWhetherExistsPath();
        System.out.println(paths.findWhetherExistsPath(5, new int[][]{{0, 1}, {0, 2}, {0, 4}, {0, 4}, {0, 1}, {1, 3}, {1, 4}, {1, 3}, {2, 3}, {3,4}}, 0, 4));
    }

    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        if (start == target) {
            return true;
        }
        Map<Integer, Set<Integer>> map = Arrays.stream(graph)
                .collect(Collectors.groupingBy(array -> array[0], Collectors.mapping(array -> array[1], Collectors.toSet())));
        boolean result = false;
        Queue<Integer> record = new LinkedList<>();
        // 遍历过的
        Set<Integer> traced = new HashSet<>();
        record.offer(start);
        while (!record.isEmpty()) {
            int spot = record.poll();
            // 记录下遍历了那些节点
            traced.add(spot);
            Optional<Set<Integer>> optional = Optional.ofNullable(map.get(spot));
            boolean contain = optional.map(e -> e.contains(target)).orElse(false);
            if (contain) {
                // 找到了，跳出
                result = true;
                break;
            }
            optional.orElse(Collections.emptySet()).forEach(record::offer);
        }
        return result;
    }
}
