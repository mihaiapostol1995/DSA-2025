package org.example;

import java.util.ArrayList;
import java.util.List;

public class ListOfLists {
    public static void main(String[] args) {
        /*
        List<List<?>> objectList = new ArrayList<>();
        List<List<Integer>> integerLIST = (List<List<Integer>>) objectList;
         */

        List<?> objectList2 = new ArrayList<>();
        List<?> integerLIST2 = (List<Integer>) objectList2;

        /*
        List<Object> objectList3 = new ArrayList<>();
        List<Object> integerLIST3 = (List<Integer>) objectList3;
         */

        ///////////////
        /*
        List<List<? extends Number>> numberList = new ArrayList<>();
        List<List<Integer>> intList = new ArrayList<>();
        numberList = intList;
         */

        List<? extends Number> numbers = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        numbers = integers;
    }
}
