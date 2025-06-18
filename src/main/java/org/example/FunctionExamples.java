package org.example;

import java.util.function.Function;

public class FunctionExamples {
    public static void main(String[] args) {
        Function<Integer, String> map = String::valueOf;
        var function = map.andThen(Integer::valueOf);
        function.apply(2);
    }
}
