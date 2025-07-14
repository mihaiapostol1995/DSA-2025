package leetcode.frequencyarray;

import java.util.Arrays;
import java.util.Comparator;

class MinimumNumberOfArrowsToBurstBalloons {
    public static void main(String[] args) {
        var m = new MinimumNumberOfArrowsToBurstBalloons();
        int[][] points = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        int[][] intervals = {
                {3, 9}, {7, 12}, {3, 8}, {6, 8},
                {9, 10}, {2, 9}, {0, 9}, {3, 9},
                {0, 6}, {2, 8}
        };
        int minArrowShots = m.findMinArrowShots(intervals);
        System.out.println(minArrowShots);
    }

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[1])); // sort and heap ar MIN to MAX (natural order) always

        int arrowsNeeded = 1;
        int highestElement = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (highestElement >= points[i][0]
                    && highestElement <= points[i][1]) {
                continue;
            } else {
                arrowsNeeded++;
                highestElement = points[i][1];
            }
        }

        return arrowsNeeded;
    }
}
