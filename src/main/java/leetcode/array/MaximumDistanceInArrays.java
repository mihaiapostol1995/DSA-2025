package leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MaximumDistanceInArrays {
    public static void main(String[] args) {
        var m = new MaximumDistanceInArrays();
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list.add(1);
        list.add(4);

        list2.add(0);
        list2.add(5);

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list);
        lists.add(list2);
        int i = m.maxDistance(lists);
        System.out.println(i);
    }

    public int maxDistance(List<List<Integer>> arrays) {
        Collections.sort(arrays, Comparator.comparingInt(List::getLast));
        Integer last = arrays.getLast().getLast();

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arrays.size() - 1; i++) {
            var list = arrays.get(i);
            min = Math.min(min, list.getFirst());
        }

        int firstMin = last - min;

        Collections.sort(arrays, Comparator.comparingInt(List::getFirst));
        Integer first = arrays.getFirst().getFirst();

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < arrays.size(); i++) {
            var list = arrays.get(i);
            max = Math.max(max, list.getLast());
        }

        int secondMin = max - first;
        return Math.max(firstMin, secondMin);
    }
}
