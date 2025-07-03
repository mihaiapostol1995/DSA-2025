package leetcode.priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class KPairsWithSmallestSums {
    public static void main(String[] args) {
        KPairsWithSmallestSums kps = new KPairsWithSmallestSums();
        kps.kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3);
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Element> pq = new PriorityQueue<>(Comparator
                .comparingInt(a -> a.integers().get(0) + a.integers().get(1)));
        pq.offer(new Element(List.of(nums1[0], nums2[0]), 0, 0));

        Set<String> visited = new HashSet<>(); // Track visited (i,j) pairs
        visited.add("0,0");

        List<List<Integer>> res = new ArrayList<>();

        while (res.size() < k && !pq.isEmpty()) {
            var poll = pq.poll();
            List<Integer> integers = poll.integers();
            res.add(List.of(integers.get(0), integers.get(1)));

            var left = poll.left();
            var right = poll.right();
            if (left + 1 < nums1.length && !visited.contains((left + 1) + "," + right)) {
                pq.offer(new Element(List.of(nums1[left + 1], nums2[right]), left + 1, right));
                visited.add((left + 1) + "," + right);
            }
            if (right + 1 < nums2.length) {
                pq.offer(new Element(List.of(nums1[left], nums2[right + 1]), left, right + 1));
                visited.add(left + "," + (right + 1));
            }
        }
        return res;
    }
}

record Element(List<Integer> integers, int left, int right) {}
