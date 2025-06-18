package org.example;

import java.time.LocalDateTime;

class PlusMinutes {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.of(1999, 1, 1, 1, 1, 1);
        System.out.println(now.plusMinutes(-1));
    }
}
