package org.example;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by mobingsen on 2020/10/17 16:07
 */
public class _17_Stream {

    public static void main(String[] args) {
        List<Integer> list = IntStream
                // 左右都是闭区间
                .rangeClosed(1, 100)
                .boxed().collect(Collectors.toList());
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

        Data17 data1 = new Data17(1, "111");
        Data17 data2 = new Data17(2, "222");
        Data17 data3 = new Data17(1, "333");
        /* final Map<Integer, Data17> map = Stream.of(data1, data2, data3).collect(Collectors.toMap(Data17::getId, Function.identity()));
         *  当tomap的key遇到相同的时会抛出异常
         * Exception in thread "main" java.lang.IllegalStateException: Duplicate key Data17{id=1, name='111', age=0}
         * 	at java.util.stream.Collectors.lambda$throwingMerger$0(Collectors.java:133)
         * 	at java.util.HashMap.merge(HashMap.java:1254)
         * 	at java.util.stream.Collectors.lambda$toMap$58(Collectors.java:1320)
         * 	at java.util.stream.ReduceOps$3ReducingSink.accept(ReduceOps.java:169)
         * 	at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
         * 	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
         * 	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
         * 	at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)
         * 	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
         * 	at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:499)
         * 	at org.example._17_Stream.main(_17_Stream.java:41)
         */
        final Map<Integer, Data17> map = Stream.of(data1, data2, data3)
                .collect(Collectors.toMap(Data17::getId, Function.identity(), (d1, d2) -> d1));
        System.out.println(map);
        List<Data17> asList = Arrays.asList(data1, data2, data3, data3);
        Map<String, List<Data17>> groupByName = asList.stream().collect(Collectors.groupingBy(Data17::getName));
        System.out.println(groupByName);
    }
}

class Data17 {
    private int id;
    private String name;
    private int age;

    public Data17() {
    }

    public Data17(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Data17(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Data17{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
