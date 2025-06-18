package org.example;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class OptionalNullabe {
    public static void main(String[] args) {
        Set<Integer> integers = Collections.unmodifiableSet(new HashSet<>());

        Optional.ofNullable(integers).ifPresent(integers1 -> System.out.println(integers1));
    }
}
