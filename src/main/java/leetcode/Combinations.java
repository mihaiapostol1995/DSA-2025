package leetcode;

import java.util.ArrayList;
import java.util.List;

class Combinations {
    public static void main(String[] args) {
        List<List<Integer>> combinations = new ArrayList<>();
        combine(4, 2, 1, new ArrayList<>(), combinations);
        System.out.println(combinations);
    }

    public static void combine(int n, int k, int index, List<Integer> current, List<List<Integer>> combinations) {
        if (current.size() == k) {
            combinations.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i < n + 1; i++) {
            current.add(i);
            combine(n, k, i + 1, current, combinations);
            current.remove(current.size() - 1);
        }
    }
}
