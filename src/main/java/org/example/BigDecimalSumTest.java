package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

class BigDecimalSumTest {

    public static void main(String[] args) {
        var b1 = new BigDecimal("29525182.37600000");
        var b2 = new BigDecimal("347333.08800000");
        var sum = b1.add(b2);

        BigDecimal bigDecimal = new BigDecimal(0.000000000000015)
                .setScale(5, RoundingMode.DOWN);
    }
}
