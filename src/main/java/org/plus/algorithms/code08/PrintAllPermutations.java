package org.plus.algorithms.code08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author mobingsen
 * @date 2021/11/28 21:33
 */
public class PrintAllPermutations {

    public static List<String> permutations(String str) {
        List<String> list = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return list;
        }
        char[] chars = str.toCharArray();
        process(chars, 0, list);
        list.sort(Comparator.naturalOrder());
        return list;
    }

    private static void process(char[] chars, int i, List<String> list) {
        if (i == chars.length) {
            list.add(String.valueOf(chars));
        }
        boolean[] visit = new boolean[26];
        for (int j = 0; j < chars.length; j++) {
            if (!visit[chars[j] - 'a']) {
                visit[chars[j] - 'a'] = true;
                swap(chars, i, j);
                process(chars, i + 1, list);
                swap(chars, i, j);
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
