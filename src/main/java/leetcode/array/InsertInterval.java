package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class InsertInterval {
    public static void main(String[] args) {
        var i = new InsertInterval();
        int[][] intervals = {
                {1, 2},
                {3, 5},
                {6, 7},
                {8, 10},
                {12, 16}
        };
        int[][] insert = i.insert(intervals, new int[]{4, 8});
        System.out.println(Arrays.deepToString(insert));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>(Arrays.asList(intervals));
        result.add(newInterval);
        result.sort(Comparator.comparing(o -> o[0]));

        for (int i = 0; i < result.size() - 1; i++) {
            int[] ints = result.get(i);
            if (i + 1 < result.size()) {
                int[] next = result.get(i + 1);
                if (ints[1] >= next[0]) {
                    ints[1] = Math.max(ints[1], next[1]);
                    result.remove(i + 1);
                    i--;
                }
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
