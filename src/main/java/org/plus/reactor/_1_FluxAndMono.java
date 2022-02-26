package org.plus.reactor;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * github：https://github.com/reactor/reactor-core
 *
 * flux：一个包含0~N个元素的响应式序列，Flux上的静态工厂允许从任意回调类型生成源。
 * Mono：Mono上的静态工厂允许从任意回调类型生成确定性的零或一序列。
 *
 * reactor实现还有jdk的Rxjava，web的webflux，netty
 * Rxjava
 * Created by mobingsen on 2020/11/1 12:04
 */
public class _1_FluxAndMono {

    public static void main(String[] args) {
        List<Long> someInts = IntStream.rangeClosed(1, 5).boxed().map(Long::new).collect(Collectors.toList());
        someInts.stream().peek(System.out::println).collect(Collectors.toList());
        Flux.fromIterable(someInts)
                .mergeWith(Flux.interval(Duration.ofNanos(1)))
                .doOnNext(_1_FluxAndMono::someObserver)
                .map(d -> d * 2)
                .take(3)
                .onErrorResume(_1_FluxAndMono::fallback)
                .doAfterTerminate(_1_FluxAndMono::incrementTerminate)
                .subscribe(System.out::println);
        Mono.fromCallable(System::currentTimeMillis)
                .flatMap(time -> Mono.firstWithValue(_1_FluxAndMono.findRecent(time), _1_FluxAndMono.findRecent(time)))
                .timeout(Duration.ofSeconds(3), Mono.just(2L))
                .doOnSuccess(_1_FluxAndMono::incrementSuccess)
                .subscribe(System.out::println);
        Tuple2<Long, Long> block = Mono.zip(
                Mono.just(System.currentTimeMillis()),
                Mono.just(2)/*.delay(Duration.ofSeconds(1))*/.map(i -> i * System.currentTimeMillis())
        ).block();
        System.out.println(block);
        Mono.fromCallable(System::currentTimeMillis)
                .repeat()
                .publishOn(Schedulers.single())
                .log("foo.bar")
                .flatMap(time ->
                                Mono.fromCallable(() -> {
                                    Thread.sleep(1000);
                                    return time;
                                }).subscribeOn(Schedulers.parallel())
                        , 8) //maxConcurrency 8
                .subscribe(System.out::println);
        Mono.fromCallable(System::currentTimeMillis)
                .repeat(5)
                .parallel(3) //parallelism
                .runOn(Schedulers.parallel())
                .doOnNext(d -> System.out.println("I'm on thread " + Thread.currentThread()))
                .subscribe();
        Scanner scanner = new Scanner(System.in);
        Flux.create(sink -> {
                    for (int i = 0; i < 5; i++) {
                        sink.next(scanner.nextInt());
                    }
                },
                // Overflow (backpressure) handling, default is BUFFER
                FluxSink.OverflowStrategy.LATEST)
                .timeout(Duration.ofSeconds(30))
                .doOnComplete(() -> System.out.println("completed!"))
                .subscribe(System.out::println);
    }

    private static void incrementSuccess(Long r) {
        System.out.println(r);
    }

    private static Mono<Long> findRecent(Long time) {
        return Mono.justOrEmpty(time);
    }

    private static void incrementTerminate() {
        System.out.println("incrementTerminate");
    }

    private static Publisher<Long> fallback(Throwable throwable) {
        return Flux.just(0L);
    }

    private static void someObserver(Long e) {
        System.out.println("someObserver: " + e);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
