package org.example.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/sort-characters-by-frequency/
 * @author mbs on 2021-06-11 9:44
 */
public class _451_FrequencySort {

    public String frequencySort(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        return Arrays.stream(s.split(""))
                .collect(Collectors.groupingBy(Function.identity()))
                .values().stream()
                .map(e -> e.stream().sorted(Comparator.naturalOrder()).collect(Collectors.joining()))
                .sorted(Comparator.comparing(e -> -e.length()))
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        final _451_FrequencySort frequencySort = new _451_FrequencySort();
        System.out.println(frequencySort.frequencySort("tree"));
        System.out.println(frequencySort.frequencySort("cccaaa"));
        System.out.println(frequencySort.frequencySort("Aabb"));
    }
}
