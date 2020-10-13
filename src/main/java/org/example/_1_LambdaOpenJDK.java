package org.example;

import java.util.Arrays;
import java.util.List;

/**
 * http://hg.openjdk.java.net/jdk8u/jdk8u/jdk/file/tip/src/share/classes/java/lang/invoke/InnerClassLambdaMetafactory.java
 * Created by 小墨 on 2020/9/4 16:45
 */
public class _1_LambdaOpenJDK {

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        arr.stream().peek(System.out::println);
        System.out.println("------------------");
        arr.forEach(System.out::print);
    }
}
