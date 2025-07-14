package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

class NonoverlappingIntervals {

    public static void main(String[] args) {
        var n = new  NonoverlappingIntervals();
        int i = n.eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}});
//        int i = n.eraseOverlapIntervals(new int[][]{{1, 2}, {1, 2}, {1, 2}});
        System.out.println(i);
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int[] cur = intervals[0];
        int max = intervals.length; // greedily assume all are correct
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < cur[1]) {
                max--;
            } else {
                cur = intervals[i];
            }
        }
        return intervals.length - max;
    }
}
