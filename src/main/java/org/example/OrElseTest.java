package org.example;

import java.util.Optional;

public class OrElseTest {
    public static void main(String[] args) {
        Optional.ofNullable("hi").orElse(fetch());
    }

    private static String fetch() {
        System.out.println("i was called");
        return "fetched";
    }
}
