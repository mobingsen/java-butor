package org.example.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/submissions/
 */
public class _395_LongestSubstring {

    public static void main(String[] args) {
        _395_LongestSubstring substring = new _395_LongestSubstring();
        System.out.println(substring.longestSubstring("a", 1));
        System.out.println(substring.longestSubstring("aaabb", 3));
        System.out.println(substring.longestSubstring("ababbc", 2));
        System.out.println(substring.longestSubstring("ababacb", 3));
        System.out.println(substring.longestSubstring("bbaaacbd", 3));
        System.out.println(substring.longestSubstring("aaaaaaaaaaaabcdefghijklmnopqrstuvwzyz", 3));
    }

    /**
     * "ababacb"
     * 3
     * 输出：
     * 5
     * 预期结果：
     * 0
     * 输入：
     * "bbaaacbd"
     * 3
     * 输出：
     * 0
     * 预期结果：
     * 3
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        Map<String, Integer> map = new HashMap<>();
        return handle(map, s, k);
    }

    public int handle(Map<String, Integer> map, String s, int k) {
        Integer n = map.get(s);
        if (n != null) {
            return n;
        }
        Set<String> cutting = Arrays.stream(s.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() < k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        if (cutting.isEmpty()) {
            map.put(s, s.length());
            return s.length();
        }
        Set<String> stringList = Stream.of(s).collect(Collectors.toSet());
        for (String c : cutting) {
            stringList = stringList.stream()
                    .flatMap(e -> Arrays.stream(e.split(c)))
                    .filter(e -> e.length() > 0)
                    .collect(Collectors.toSet());
        }
        return stringList.stream()
                .map(e -> handle(map, e, k))
                .max(Comparator.comparingInt(Integer::intValue))
                .orElse(0);
    }
}
