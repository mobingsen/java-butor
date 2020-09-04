package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        while ((line = sc.nextLine()) == null || line.length() <= 0) {}
        if (line.length() == 1) {
            System.out.println(1);
            return;
        }
        final String word = line;
        Arrays.stream(line.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(v -> 1)))
                .entrySet().stream().filter(e -> e.getValue() >= 2).map(Map.Entry::getKey)
                .map(w -> word.lastIndexOf(w) - word.indexOf(w) + 1).max(Comparator.comparingInt(Integer::intValue))
                .ifPresent(System.out::println);
    }
}
