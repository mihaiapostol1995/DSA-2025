package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class TheSkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // create the events! most important thing, the "trick"

        List<int[]> events = new ArrayList<>();
        for (int[] building: buildings) {
            events.add(new int[]{building[0], -building[1]}); // entering on the negative
            events.add(new int[]{building[1], building[2]}); // leaving on positive
        }

        events.sort((int[] a, int[] b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(0); // for the down segment
        var previousMaxX = 0; // for overlapping buildings
        List<List<Integer>> result = new ArrayList<>();

        for (int[] event: events) {
            int x = event[0], height = event[1];
            if (height < 0) {
                // this is WHY we used negatives! so you know when to add and when to remove
                maxHeap.add(-height);
            } else {
                maxHeap.remove(height);
            }

            var curMax = maxHeap.peek();
            if (curMax != previousMaxX) {
                result.add(List.of(x, curMax));
                previousMaxX = curMax;
            }
        }
        return result;
    }
}
