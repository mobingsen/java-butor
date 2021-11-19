package org.example.algorithms.code07;

import java.util.Arrays;

/**
 * @author mobingsen
 * @date 2021/11/19 23:45
 */
public class LowestLexicography {

    public static void main(String[] args) {
        String[] strings = {"jibw", "ji", "jp", "bw", "jibw"};
        System.out.println(lowestString(strings));
    }

    private static String lowestString(String[] strings) {
        if (null == strings || strings.length == 0) {
            return "";
        }
        Arrays.sort(strings, (a, b) -> (a + b).compareTo(b + a));
        // 依次拼接
        return String.join("", strings);
    }
}
