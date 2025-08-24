package leetcode.priorityqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// GOOD problem for binary search! you can find a RANGE, wow
class FindKClosestElements {
    public static void main(String[] args) {
        var f = new FindKClosestElements();
        f.findClosestElements(new int[]{1, 3, 5, 7, 8, 9, 10, 12, 14, 15}, 4, 11);
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((el1, el2) -> {
            if (Math.abs(el1 - x) != Math.abs(el2 - x)) {
                return Math.abs(el2 - x) - Math.abs(el1 - x);
            } else {
                return el2 - el1;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            if (pq.size() < k) {
                pq.add(arr[i]);
                continue;
            }

            var curr = arr[i];
            var peek = pq.peek();

            if (Math.abs(curr - x) < Math.abs(peek - x)) {
                pq.poll();
                pq.add(curr);
            } else {
                if (curr < peek) {
                    pq.poll();
                    pq.add(curr);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(pq.poll());
        }
        Collections.sort(result);
        return result;
    }
}
