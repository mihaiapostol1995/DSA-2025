package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        merge(intervals);
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new ArrayList<>();
        int[] curr = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            if (start <= curr[1]) {
                curr[1] = Math.max(curr[1], end);
            } else {
                result.add(curr);
                // prepare next
                curr = intervals[i];
            }
        }
        if (!result.contains(curr)) {
            result.add(curr);
        }

        return result.toArray(new int[result.size()][]);
    }
}
