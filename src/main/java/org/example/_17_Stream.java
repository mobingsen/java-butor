package org.example;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * Created by 小墨 on 2020/10/17 16:07
 */
public class _17_Stream {

    public static void main(String[] args) {
        List<Integer> list = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        Iterator<Integer> iterator = list.iterator();
        IntSummaryStatistics statistics = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
                .collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(statistics.getSum());
        int sum = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(list.iterator(), Spliterator.ORDERED), false)
                .limit(50).mapToInt(Integer::intValue).sum();
        System.out.println(sum);
        String[] stringArr = list.stream().map(String::valueOf).toArray(String[]::new);
        String line = Arrays.stream(stringArr)
                .map(String::toUpperCase)
                .collect(Collectors.joining("_"));
        System.out.println(line);
    }
}
