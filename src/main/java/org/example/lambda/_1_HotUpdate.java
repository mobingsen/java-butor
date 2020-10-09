package org.example.lambda;

/**
 * Created by xiaomo on 2020/9/29 9:46
 */
public class _1_HotUpdate {

    public static void main(String[] args) {
        HotFunc hotFunc = System.out::println;
        hotFunc.print("hello");
    }
}

@FunctionalInterface
interface HotFunc {

    void print(String word);
}
