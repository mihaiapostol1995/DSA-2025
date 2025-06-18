//package org.example;
//
//import lombok.extern.slf4j.Slf4j;
//import org.reactivestreams.Subscriber;
//import org.reactivestreams.Subscription;
//import reactor.core.publisher.*;
//import reactor.core.scheduler.Schedulers;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Consumer;
//import java.util.stream.Stream;
//
//import static java.util.stream.Collectors.toList;
//import static reactor.core.publisher.Flux.just;
//import static reactor.core.publisher.Flux.range;
//
//
//@Slf4j
//public class Reactor {
//
//    static final Sinks.Many<Object> CHARACTERS_MANY = Sinks.many().multicast().onBackpressureBuffer();
//    static Consumer<List<Character>> CONSUMER;
//
//    public static void main(String[] args) throws InterruptedException {
//        //from https://www.baeldung.com/reactor-core
//        //firstSubscriberWithCustomImplementation();
//        //combineSubscribers();
//        //parallelFlux();
//        //fiveMainThreadsThatSeemParallel();
//
//        //from https://www.baeldung.com/java-flux-create-generate
//        //generateCharacters(); //this is SynchronousSink<Character> sink
//        //createCharacterSequence(); //this is async - FluxSink<Character>
//
//        //from baeldung.com/java-reactor-flux-vs-mono
//        //monoVsFlux();
//
//        //https://www.baeldung.com/java-reactor-map-flatmap
//        // The difference is that flatMap will return a publisher
//        //mapFlux();
//        //flatmapFlux();
//
//        //https://stackoverflow.com/questions/49965867/when-flatmap-will-listen-to-multiple-sources-concurrently
//        //flatMapConcurrency();
//
//        //https://github.com/spring-attic/reactive-streams-commons/issues/21
//        //buffer();
//        //window();
//
//        //https://stackoverflow.com/questions/50528668/why-does-share-have-no-effect-on-cold-sources-autoconnect-vs-refcount
//        //shareReactor();
//        //autoconnectReactor();
//        //https://stackoverflow.com/questions/58375967/how-should-i-use-flux-publish
//        //replayReactor();
//        //cacheReactor();
//        //twoSubscribersReactor(); //nothing happens because i didn't use PUBLISH
//
//        //sinksBlotter();
//    }
//
//    private static void sinksBlotter() {
//        new Thread(() -> List.of('a', 'b', 'c', 'd', 'e', 'f', 'g').forEach(character -> {
//            log.debug("--------------------> emitting: " + character);
//            CHARACTERS_MANY.emitNext(character, Sinks.EmitFailureHandler.FAIL_FAST);
//            sleep(1000);
//        })).start();
//
//        Flux<Object> flux = CHARACTERS_MANY.asFlux();
//
//        System.out.println("first subscriber");
//        flux
//                .doOnNext(c -> log.debug("doing on next on thread: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed"))
//                .doOnCancel(() -> System.out.println("cancelled"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed"))
//                .subscribe(c -> log.debug("this is my character as first subscriber: " + c));
//
//        sleep(4000);
//
//        System.out.println("next subscriber");
//        flux
//                .doOnNext(c -> log.debug("doing for NEXT SUBSCRIBER: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed as NEXT SUBSCRIBER:"))
//                .doOnCancel(() -> System.out.println("cancelled as NEXT SUBSCRIBER:"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed as NEXT SUBSCRIBER"))
//                .subscribe(c -> log.debug("this is my character as NEXT SUBSCRIBER: " + c));
//        System.out.println("done second subscriber");
//
//        System.out.println("third subscriber");
//        flux
//                .doOnNext(c -> log.debug("doing for THIRD subscriber: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed as THIRD subscriber"))
//                .doOnCancel(() -> System.out.println("cancelled as THIRD subscriber"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed as THIRD subscriber"))
//                .subscribe(c -> log.debug("this is my character as THIRD subscriber: " + c));
//        System.out.println("done third subscriber");
//    }
//
//    private static void shareReactor() {
//        System.out.println("--- share reactor");
//
//        //Flux<Integer> shared = Flux.just(1, 2).publish().refCount(); //share, but NOT a real-life scenario
//        Flux<Character> shared = Flux.create((FluxSink<Character> sink)
//                -> CONSUMER = getListConsumer(sink)
//        )
//                //.subscribeOn(Schedulers.parallel());
//                .publish()
//                .refCount();
//
//        shared
//                .doOnNext(c -> log.debug("doing on next on thread: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed"))
//                .doOnCancel(() -> System.out.println("cancelled"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed"))
//                .subscribe(c -> log.debug("this is my character as first subscriber: " + c));
//        sleep(1000); //subscribe will trigger create, wait so that the consumer is not null
//        System.out.println("starting to accept");
//        new Thread(() -> {
//                log.debug("starting to accept inside thread");
//                CONSUMER.accept(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g'));
//        }).start();
//        System.out.println("finished accepting");
//
//        sleep(1000);
//
//        log.debug("next subscriber");
//        shared
//                .doOnNext(c -> log.debug("doing for NEXT SUBSCRIBER: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed as NEXT SUBSCRIBER:"))
//                .doOnCancel(() -> System.out.println("cancelled as NEXT SUBSCRIBER:"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed as NEXT SUBSCRIBER"))
//                .subscribe(c -> log.debug("this is my character as NEXT SUBSCRIBER: " + c));
//        System.out.println("done");
//    }
//
//    private static void autoconnectReactor() {
//        System.out.println("--- autoconnect reactor");
//
//        //Flux<Integer> shared = Flux.just(1, 2).publish().refCount(); //share, but NOT a real-life scenario
//        Flux<Character> shared = Flux.create((FluxSink<Character> sink)
//                        -> CONSUMER = getListConsumer(sink)
//                )
//                //.subscribeOn(Schedulers.parallel());
//                .publish()
//                .autoConnect();
//
//        shared
//                .doOnNext(c -> log.debug("doing on next on thread: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed"))
//                .doOnCancel(() -> System.out.println("cancelled"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed"))
//                .subscribe(c -> log.debug("this is my character as first subscriber: " + c));
//        sleep(1000); //subscribe will trigger create, wait so that the consumer is not null
//        System.out.println("starting to accept");
//        new Thread(() -> {
//            log.debug("starting to accept inside thread");
//            CONSUMER.accept(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g'));
//        }).start();
//        System.out.println("finished accepting");
//
//        sleep(1000);
//
//        log.debug("next subscriber");
//        shared
//                .doOnNext(c -> log.debug("doing for NEXT SUBSCRIBER: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed as NEXT SUBSCRIBER:"))
//                .doOnCancel(() -> System.out.println("cancelled as NEXT SUBSCRIBER:"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed as NEXT SUBSCRIBER"))
//                .subscribe(c -> log.debug("this is my character as NEXT SUBSCRIBER: " + c));
//        System.out.println("done");
//    }
//
//    private static void replayReactor() {
//        System.out.println("--- replayReactor reactor");
//
//        //Flux<Integer> shared = Flux.just(1, 2).publish().refCount(); //share, but NOT a real-life scenario
//        Flux<Character> shared = Flux.create((FluxSink<Character> sink)
//                        -> CONSUMER = getListConsumer(sink)
//                )
//                //.subscribeOn(Schedulers.parallel());
//                .replay()
//                .autoConnect();
//
//        shared
//                .doOnNext(c -> log.debug("doing on next on thread: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed"))
//                .doOnCancel(() -> System.out.println("cancelled"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed"))
//                .subscribe(c -> log.debug("this is my character as first subscriber: " + c));
//        sleep(1000); //subscribe will trigger create, wait so that the consumer is not null
//        System.out.println("starting to accept");
//        new Thread(() -> {
//            log.debug("starting to accept inside thread");
//            CONSUMER.accept(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g'));
//        }).start();
//        System.out.println("finished accepting");
//
//        sleep(4000);
//
//        System.out.println("next subscriber");
//        shared
//                .doOnNext(c -> log.debug("doing for NEXT SUBSCRIBER: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed as NEXT SUBSCRIBER:"))
//                .doOnCancel(() -> System.out.println("cancelled as NEXT SUBSCRIBER:"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed as NEXT SUBSCRIBER"))
//                .subscribe(c -> log.debug("this is my character as NEXT SUBSCRIBER: " + c));
//        System.out.println("done");
//    }
//
//    private static void cacheReactor() {
//        System.out.println("--- cacheReactor reactor");
//
//        //Flux<Integer> shared = Flux.just(1, 2).publish().refCount(); //share, but NOT a real-life scenario
//        Flux<Character> shared = Flux.create((FluxSink<Character> sink)
//                        -> CONSUMER = getListConsumer(sink)
//                )
//                //.subscribeOn(Schedulers.parallel());
//                .cache();
//
//        shared
//                .doOnNext(c -> log.debug("doing on next on thread: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed"))
//                .doOnCancel(() -> System.out.println("cancelled"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed"))
//                .subscribe(c -> log.debug("this is my character as first subscriber: " + c));
//        sleep(1000); //subscribe will trigger create, wait so that the consumer is not null
//        System.out.println("starting to accept");
//        new Thread(() -> {
//            log.debug("starting to accept inside thread");
//            CONSUMER.accept(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g'));
//        }).start();
//        System.out.println("finished accepting");
//
//        sleep(4000);
//
//        System.out.println("next subscriber");
//        shared
//                .doOnNext(c -> log.debug("doing for NEXT SUBSCRIBER: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed as NEXT SUBSCRIBER:"))
//                .doOnCancel(() -> System.out.println("cancelled as NEXT SUBSCRIBER:"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed as NEXT SUBSCRIBER"))
//                .subscribe(c -> log.debug("this is my character as NEXT SUBSCRIBER: " + c));
//        System.out.println("done");
//    }
//
//    private static void twoSubscribersReactor() {
//        System.out.println("--- twoSubscribersReactor");
//
//        //Flux<Integer> shared = Flux.just(1, 2).publish().refCount(); //share, but NOT a real-life scenario
//        Flux<Character> shared = Flux.create((FluxSink<Character> sink)
//                        -> CONSUMER = getListConsumer(sink)
//                );
//                //.subscribeOn(Schedulers.parallel());
//
//        shared
//                .doOnNext(c -> log.debug("doing on next on thread: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed"))
//                .doOnCancel(() -> System.out.println("cancelled"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed"))
//                .subscribe(c -> log.debug("this is my character as first subscriber: " + c));
//        sleep(1000); //subscribe will trigger create, wait so that the consumer is not null
//        System.out.println("starting to accept");
//        new Thread(() -> {
//            log.debug("starting to accept inside thread");
//            CONSUMER.accept(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g'));
//        }).start();
//        System.out.println("finished accepting");
//
//        sleep(4000);
//
//        System.out.println("next subscriber");
//        shared
//                .doOnNext(c -> log.debug("doing for NEXT SUBSCRIBER: " + Thread.currentThread().getName()))
//                .doOnComplete(() -> System.out.println("i completed as NEXT SUBSCRIBER:"))
//                .doOnCancel(() -> System.out.println("cancelled as NEXT SUBSCRIBER:"))
//                .doOnSubscribe(subscription -> log.debug("i subscribed as NEXT SUBSCRIBER"))
//                .subscribe(c -> log.debug("this is my character as NEXT SUBSCRIBER: " + c));
//        System.out.println("done");
//    }
//
//
//
//    private static void window() {
//        Flux<Flux<Integer>> map = range(1, 1_000_000)
//                .window(256)
//                .map(w ->
//                        {
//                            Flux<Integer> map1 = w.subscribeOn(Schedulers.parallel())
//                                    .map(Object::hashCode);
//                            return map1;
//                        }
//                );
//    }
//
//    private static void buffer() {
//        Flux<List<Integer>> listFlux = range(1, 1_000_000)
//                .buffer(256)
//                .flatMap(
//                        list ->
//                        {
//                            Flux<List<Integer>> just = just(list);
//                            Flux<List<Integer>> listFlux1 = just
//                                    .subscribeOn(Schedulers.parallel());
//                            return listFlux1
//                                .map(v ->
//                                        {
//                                            Stream<Integer> stream = v.stream();
//                                            return stream
//                                                .map(Object::hashCode)
//                                                .collect(toList());
//                                        }
//                                );
//                        }
//                );
//        Flux<Integer> integerFlux = listFlux.flatMapIterable(f -> f);
//    }
//
//    private static void flatMapConcurrency() {
//        //number of threads depends on the number of elements in the Flux.range
//        range(1, 6).hide()
//                .flatMap(v -> range(v * 10, 2)
//                        .log()
//                        //.publishOn(Schedulers.newParallel("foo", 3))
//                )
//                .log()
//                .flatMap(v -> range(10 * v, 2))
//                .log()
//                .flatMap(v -> range(10 * v, 2))
//                .log()
//                .blockLast(); //for test purpose
//    }
//
//    private static void flatmapFlux() {
//        List<String> characters = new ArrayList<>();
//
//        just("baeldung", ".", "com") //this is reactor.Flux.Array.1
//                .doOnNext(s1 -> log.debug("FIRST LOG MESSAGE: i have started working on this string: " + s1))
//                .log()
//                .map(string -> {
//                    log.debug("requesting for string {}", string);
//                    return string;
//                })
//                // compare the output WITHOUT publishOn(). it seems like flatMap() and just() are SUBSCRIBERS too, which are
//                // triggered by subscribe()! see the generated logs
//                .publishOn(Schedulers.parallel())
//                .flatMap(s->
//                        just(s.toUpperCase().split(""))
//                        .log()
//                        .doOnNext(s1 -> log.debug("inside the flatmap i have this string: " + s1))
//                )
//                .doOnNext(s -> log.debug("after flatmap i am processing this string: " + s))
//                .log()
//                .subscribe(string -> {
//                    log.debug("i am getting this element {} from thread {}", string, Thread.currentThread().getName());
//                    characters.add(string);
//                });
//        //if i don't sleep, then the consumer will not finish in time for the publishOn() flow
//        //sleep();
//        System.out.println(characters);
//    }
//
//    private static void mapFlux() {
//        just("baeldung", ".", "com")
//                .map(String::toUpperCase)
//                .log()
//                .subscribe();
//    }
//
//    public static void monoVsFlux() {
//        Flux<String> stringFlux = just("Hello", "Baeldung", "Error")
//                .map(str -> {
//                    if (str.equals("Error"))
//                        throw new RuntimeException("Throwing Error");
//                    return str;
//                });
//        stringFlux.log()
//                .subscribe();
//    }
//
//    public static void createCharacterSequence() throws InterruptedException {
//        Flux<Character> characterFlux = Flux.create((FluxSink<Character> sink)
//                -> CONSUMER = getListConsumer(sink)
//        );
//
//        //prepare the threads
//        Thread producerThread1 = new Thread(() -> CONSUMER.accept(List.of('a', 'b', 'c')));
//        Thread producerThread2 = new Thread(() -> CONSUMER.accept(List.of('d', 'e', 'f', 'g')));
//
//        // trigger the whole flow
//        List<Character> consolidated = new ArrayList<>();
//        characterFlux.subscribe(consolidated::add);
//
//        // then the threads will start producing (first the subscriber subscribes, of course, before any singal is emitted, otherwise
//        // the consumers/subscribers won't catch anything)
//        producerThread1.start();
//        producerThread2.start();
//        producerThread2.join();
//        producerThread1.join();
//    }
//
//    private static Consumer<List<Character>> getListConsumer(FluxSink<Character> emitter) {
//        return (List<Character> items) -> items.forEach(singleItem -> {
//                log.debug("---------------> emitting the next item from this thread after one second of sleeping: " + singleItem);
//                sleep(1000);
//                emitter.next(singleItem);
//        });
//    }
//
//    public static void generateCharacters() {
//        Flux.generate(() -> 97, (Integer state, SynchronousSink<Character> sink) -> {
//            log.debug("I received character {} for sink {}", state, sink);
//            char value = (char) state.intValue();
//            sink.next(value);
//            if (value == 'z') {
//                sink.complete();
//            }
//            return state + 1;
//        })
//            //i added these:
//            .log()
//            .subscribe(character -> log.debug("the subscriber got this character " + character));
//    }
//
//    // only main thread is printed
//    private static void fiveMainThreadsThatSeemParallel() {
//        ConnectableFlux<Object> publish = Flux
//                .create(fluxSink -> {
//                    while (true) {
//                        log.debug("thread name of the sink: " + Thread.currentThread());
//                        long time = System.currentTimeMillis();
//                        log.debug("publishing next time: " + time);
//                        sleep(1000);
//                        fluxSink.next(time);
//                    }
//                })
//                .publish();
//
//        log.debug("first thread");
//        new Thread(() -> {
//            log.debug("first thread name: " + Thread.currentThread().getName());
//            publish
//                    .subscribe((l) -> System.out.println(l + "_" + Thread.currentThread().getName()));
//            log.debug("starting to connect: " + Thread.currentThread().getName());
//        }).start();
//        log.debug("second thread");
//        new Thread(() -> {
//            log.debug("second thread name: " + Thread.currentThread().getName());
//            publish
//                    .subscribe((l) -> System.out.println(l + "_" + Thread.currentThread().getName()));
//            //publish.connect();
//            log.debug("starting to connect: " + Thread.currentThread().getName());
//        }).start();
//        log.debug("third thread");
//        new Thread(() -> {
//            log.debug("third thread name: " + Thread.currentThread().getName());
//            publish
//                    .subscribe((l) -> System.out.println(l + "_" + Thread.currentThread().getName()));
//            //publish.connect();
//            log.debug("starting to connect: " + Thread.currentThread().getName());
//        }).start();
//        log.debug("forth thread");
//        new Thread(() -> {
//            log.debug("forth thread name: " + Thread.currentThread().getName());
//            publish
//                    .subscribe((l) -> System.out.println(l + "_" + Thread.currentThread().getName()));
//            //publish.connect();
//            log.debug("starting to connect: " + Thread.currentThread().getName());
//        }).start();
//        log.debug("fifth thread");
//        new Thread(() -> {
//            log.debug("fifth thread name: " + Thread.currentThread().getName());
//            publish
//                    .subscribe((l) -> System.out.println(l + "_" + Thread.currentThread().getName()));
//            //publish.connect();
//            log.debug("starting to connect: " + Thread.currentThread().getName());
//        }).start();
//
//        log.debug("calling publish");
//        publish.connect();
//    }
//
//    private static void parallelFlux() {
//        ParallelFlux<Object> publish = Flux.create(fluxSinkEmitter -> {
//                    while(true) {
//                        log.debug("thread name of the sink: " + Thread.currentThread());
//                        long time = System.currentTimeMillis();
//                        log.debug("publishing next time: " + time);
//                        sleep(1000);
//                        fluxSinkEmitter.next(time);
//                    }
//                })
//                .parallel(2);
//
//        log.debug("first thread");
//        new Thread(() -> {
//            log.debug("first thread name: " + Thread.currentThread().getName());
//            publish
//                    //.log()
//                    .subscribe((l) -> System.out.println(l + Thread.currentThread().getName()));
//            log.debug("starting to connect: " + Thread.currentThread().getName());
//        }).start();
//
//        log.debug("second thread");
//        new Thread(() -> {
//            log.debug("second thread name: " + Thread.currentThread().getName());
//            publish
//                    //.log()
//                    .subscribe((l) -> System.out.println(l + Thread.currentThread().getName()));
//            log.debug("starting to connect: " + Thread.currentThread().getName());
//        }).start();
//    }
//
//    private static void getPrintln(Object l) {
//        System.out.println(l + Thread.currentThread().getName());
//    }
//
//    private static void combineSubscribers() {
//        List<String> stringElements = new ArrayList<>();
//        just(1, 2, 3, 4)
//                .log()
//                .map(i -> i * 2)
//                .zipWith(range(0, Integer.MAX_VALUE),
//                        (one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two))
//                .subscribe(stringElements::add);
//        System.out.printf(stringElements.toString());
//    }
//
//    private static void firstSubscriberWithCustomImplementation() {
//        List<Integer> elements = new ArrayList<>();
//
//        just(0, 1, 2, 3, 4, 5, 6, 7)
//                .log()
//                .map(i -> i + 3)
//                .zipWith(range(40, 8),
//                        (firstStream, secondStream) -> firstStream + secondStream)
//                .subscribe(new Subscriber<Integer>() {
//                    private Subscription subscription;
//                    int onNextAmount;
//
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        this.subscription = s;
//
//                        log.debug("Starting to request for elements.");
//                        subscription.request(2);
//                        log.debug("I requested elements");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        log.debug("adding this element: " + integer);
//                        elements.add(integer);
//                        log.debug("I added the element");
//                        onNextAmount++;
//                        sleep(1000);
//
//                        if (onNextAmount % 2 == 0) {
//                            log.debug("Requesting two elements from onNext()");
//                            // downstream (the subsriber) can tell an upstream to send it fewer data in order to
//                            // prevent it from being overwhelmed.
//                            subscription.request(2);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        log.error("I threw this error: " + t.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        log.debug("I completed.");
//                    }
//                });
//    }
//
//    private static void sleep(int millis) {
//        try {
//            Thread.sleep(millis);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
