package org.plus;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mbs on 2021/5/28 9:05
 */
public class _41_CollectorsJoin {

    public static void main(String[] args) {
        final String str = Stream.of(null, null, "")
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
        System.out.println("->" + str);
    }
}
