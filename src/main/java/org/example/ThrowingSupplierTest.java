package org.example;

import org.springframework.util.function.ThrowingSupplier;

class ThrowingSupplierTest {

    public static void main(String[] args) {
        ThrowingSupplier<String> supplier = () -> throwException();

        supplier.get();
    }

    static String throwException() throws Exception {
        throw new Exception();
    }
}
