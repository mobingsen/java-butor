package org.example.algorithms.code08;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mobingsen
 * @date 2021/11/28 21:17
 */
public class PrintAllSubSequences {

    public static void printAllSubSequence(String str) {
        char[] chars = str.toCharArray();
        process(chars, 0);
    }

    private static void process(char[] chars, int i) {
        if (i == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }
        process(chars, i + 1);
        char tmp = chars[i];
        chars[i] = 0;
        process(chars, i + 1);
        chars[i] = tmp;
    }

    public static void func2(String str) {
        char[] chars = str.toCharArray();
        process(chars, 0, new ArrayList<>());
    }

    private static void process(char[] chars, int i, List<Character> characters) {
        if (i == chars.length) {
            printList(characters);
            return;
        }
        List<Character> copy = copyList(characters);
        copy.add(chars[i]);
        process(chars, i + 1, characters);
        List<Character> noInclude = copyList(characters);
        process(chars, i + 1, noInclude);
    }

    private static List<Character> copyList(List<Character> characters) {
        List<Character> list = new ArrayList<>(characters.size());
        list.addAll(characters);
        return list;
    }

    private static void printList(List<Character> characters) {
        System.out.println(characters);
    }
}
