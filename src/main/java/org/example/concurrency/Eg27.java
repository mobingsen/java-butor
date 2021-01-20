package org.example.concurrency;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * 对于一个随机数生成器来说，有两个要素需要考量：
 * 1.随机数生成器的种子
 * 2.具体的随机数生成算法（函数）
 * 对于ThreadLocalRandom来说，其随机数生成器的种子是存放在每个线程的ThreadLocal中的
 * Created by mobingsen on 2020/7/15 20:12
 */
public class Eg27 {

    public static void main(String[] args) {
        Random random = new Random();
        IntStream.range(0, 10).boxed().map(i -> random.nextInt(10)).forEach(System.out::println);
        System.out.println("==================");
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        IntStream.range(0, 10).boxed().map(i -> threadLocalRandom.nextInt(10)).forEach(System.out::println);
    }
}
