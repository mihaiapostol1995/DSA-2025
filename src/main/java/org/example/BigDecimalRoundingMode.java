package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

class BigDecimalRoundingMode {
    public static void main(String[] args) {

        System.out.println(new BigDecimal("100"));
        System.out.println(new BigDecimal("100").setScale(2, RoundingMode.UNNECESSARY));
    }
}
