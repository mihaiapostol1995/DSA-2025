package org.example;

import java.time.LocalDate;
import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args) {
        Supplier<LocalDate> supplier = () -> {
            var now = LocalDate.now();
            return now;
        };

        System.out.println(supplier.get());
        System.out.println(supplier.get());
    }
}
