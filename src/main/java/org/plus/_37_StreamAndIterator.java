package org.plus;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by mbs on 2021/1/22 11:19
 */
public class _37_StreamAndIterator {

    public static void main(String[] args) {
        final List<String> stringList = IntStream.rangeClosed(1, 100_0000)
                .boxed()
                .map(i -> UUID.randomUUID().toString())
                .collect(Collectors.toList());
        final long start = System.nanoTime();
        stringList.stream().forEach(System.out::println);
        final long end1 = System.nanoTime();
        stringList.parallelStream().forEach(System.out::println);
        final long end2 = System.nanoTime();
        while (stringList.iterator().hasNext()) {
            final String next = stringList.iterator().next();
            System.out.println(next);
        }
        final long end3 = System.nanoTime();
        System.out.printf("stream: %s, parallelStream: %s, iterator: %s%n", (end1 - start), (end2 - end1), (end3 - end2));
    }
}
