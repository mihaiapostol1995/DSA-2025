package org.example;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s-> toUpperCase(s))
                .thenApply(s -> toLowerCase(s))
                .thenApply(s -> toLowerCase(s))
                // throw runtime exception, then continue
                .thenApply(s -> throwException())
                .thenApply(s -> returnMe(s))
                .thenApply(s -> returnMe(s))
                .exceptionally(e -> "exception was thrown")
                .thenAccept(e -> printMe())
                .thenAccept(e -> printMe())
                .thenAccept(e -> printMe());
                //.thenApply(voidz -> returnMe());
    }

    private static String throwException() {
        throw new RuntimeException();
    }

    private static void printMe() {
        System.out.println("I continued and I finished in the end.");
    }

    private static String toUpperCase(String s) {
        return s.toUpperCase();
    }

    private static String toLowerCase(String s) {
        return s.toLowerCase();
    }

    private static String returnMe(String s) {
        System.out.println("i was called");
        return "another hello";
    }
}
