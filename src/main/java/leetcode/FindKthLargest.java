package leetcode;

import java.util.PriorityQueue;

class FindKthLargest {
    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{1,2,3,4,5,6,7}, 3));
    }

    static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            if (minHeap.peek() < nums[i]) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }
}
