package org.example;

import io.vavr.control.Try;

import java.util.concurrent.CompletableFuture;

public class TryWithCompletableFuture {
    public static void main(String[] args) throws InterruptedException {
        Object obj = Try.of(() -> CompletableFuture
                        .supplyAsync(() -> {throw new RuntimeException("I failed");})
//                        .exceptionally(throwable -> {
//                            System.out.println("from completable future:" + throwable);
//                            //return throwable.toString();
//                            return Try.failure(throwable);
//                            })
                        .get())
                //.onFailure(throwable -> System.out.println("from try " + throwable))
                .getOrElseThrow(throwable -> new RuntimeException("I threw from getOrElseThrow: ", throwable));

        Thread.sleep(1000);
        System.out.println("The end object is: " + obj);
    }
}
