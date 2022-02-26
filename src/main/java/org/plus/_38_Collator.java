package org.plus;

import java.text.Collator;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * Created by mbs on 2021/1/22 14:07
 */
public class _38_Collator {

    public static void main(String[] args) {
        final Collator instance = Collator.getInstance(Locale.CHINA);
        Stream.of("add", "acd", "ddd", "ccc")
                .sorted(instance)
                .forEach(System.out::println);
    }
}
