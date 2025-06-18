package org.example;

import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Stream;

public class StreamsGenerator {
    public static void main(String[] args) {
        Iterator<String> iterator = Stream.generate(() -> "hello " + UUID.randomUUID())
                .iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
