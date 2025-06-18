package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MapMulti {

    public static void main(String[] args) {
        // method reference
        BiConsumer<List<Integer>, Consumer<Integer>> biConsumer = (li, cons) -> {
            li.forEach(cons);
        };

        List.of(1, 2).forEach(System.out::println);

//        Map<Integer, List<Integer>> map = new HashMap<>();
//        map.putIfAbsent(1, List.of(1)).add(1);

        System.out.println(LocalDate.now().isBefore(LocalDate.now()));

        HashMap<String, String> h = new HashMap<>(3);
        h.keySet().forEach(hashEntry -> System.out.printf("h is printed"));

        //test double conversion
        double d1 = 22458123.76;
        double d2 = -5370459.52;
        double sum = d1 + d2;
        System.out.println(sum);

        System.out.println("addition");
        BigDecimal b1 = BigDecimal.valueOf(22458123.76);
        BigDecimal b2 = BigDecimal.valueOf(-5370459.52);
        System.out.println(b1.add(b2));

        System.out.println("values");
        System.out.println(BigDecimal.valueOf(sum));
        System.out.println(b1.add(b2).doubleValue());
        System.out.println(b1.add(b2).toPlainString());
    }
}
