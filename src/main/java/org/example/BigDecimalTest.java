package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTest {

    public static void main(String[] args) {
        double d = 2;
        System.out.println(BigDecimal.valueOf(d));
        System.out.println(BigDecimal.valueOf(d).equals(BigDecimal.valueOf(2)));

        System.out.println(BigDecimal.valueOf(d).toPlainString()
                .equals(BigDecimal.valueOf(2).toPlainString()));

        System.out.println(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_DOWN));

        System.out.println(new BigDecimal("0,99").equals(new BigDecimal("0,99")));
    }
}
