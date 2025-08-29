package leetcode.priorityqueue;

import java.util.PriorityQueue;

class KthLargest {
    int k;
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public KthLargest(int k, int[] nums) {
        this.k = k;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (minHeap.size() < k) {
                minHeap.add(nums[i]);
            } else {
                var peek = minHeap.peek();
                if (nums[i] > peek) {
                    minHeap.poll();
                    minHeap.offer(nums[i]);
                }
            }
        }
    }

    public int add(int val) {
        if (minHeap.size() < k) {
            minHeap.add(val);
        } else {
            var peek = minHeap.peek();
            if (val > peek) {
                minHeap.poll();
                minHeap.offer(val);
            }
        }
        return minHeap.peek();
    }
}
