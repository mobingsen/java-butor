package org.plus;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Created by mbs on 2021/1/14 10:24
 */
public class _33_LambdaPeek {

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        IntStream.rangeClosed(1, 10)
                .boxed()
                .map(Data::new)
                .peek(data -> data.setSort(ai.incrementAndGet()))
                .forEach(System.out::println);
    }

    static class Data {
        private int id;

        private int sort;

        public Data(int id) {
            this.id = id;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        @Override
        public String toString() {
            return this.id + "\t" + this.sort;
        }
    }
}
