package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class DateInclusive {
    public static void main(String[] args) {
        System.out.println(LocalDate.now().isBefore(LocalDate.now()));

        System.out.println("lll".split("_")[0]);

        List<String> integers = new ArrayList<>();
        integers.add(null);
        integers.add(null);
        for (String i : integers) {
            System.out.println("Test null");
        }

        System.out.println("filterName==%s&excludeDefaultConfiguration=true".formatted("MY_FILTER"));
    }
}
