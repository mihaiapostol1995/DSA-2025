package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
//        int[] A = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
//        int k = 3;

        int[] A = new int[]{1};
        int k = 1;

        TreeSet<Integer> set = new TreeSet<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i : A) {
            k--;
            if (k < 0) {
                arrayList.add(set.last());
                set.remove(queue.poll());
            }
            set.add(i);
            queue.add(i);
        }
        arrayList.add(set.last());

        System.out.println(arrayList);
    }
}
