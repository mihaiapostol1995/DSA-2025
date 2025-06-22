package leetcode.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

class RotateArray {

    public static void main(String[] args) {
        rotate(new int[]{1,2,3,4,5,6,7}, 3);
    }

    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        if (k == 0) {
            return;
        }

        k = k % nums.length;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = nums.length - k; i < nums.length; i++) {
            queue.offer(nums[i]);
        }
        for (int i = 0; i < nums.length - k; i++) {
            queue.offer(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = queue.poll();
        }
    }

    // alternative: use reverse on 3 arrays
    // alternative: rotated[(i + k) % n] = nums[i];
}
