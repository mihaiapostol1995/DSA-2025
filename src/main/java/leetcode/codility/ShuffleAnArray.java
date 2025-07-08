package leetcode.codility;

import java.util.Arrays;
import java.util.Random;

class ShuffleAnArray {
    public static void main(String[] args) {
        ShuffleAnArray shuffleAnArray = new ShuffleAnArray();
    }
}

class Solution {

    int[] nums;
    int[] shuffled;
    Random random;

    public Solution(int[] nums) {
        this.nums = Arrays.copyOf(nums, nums.length);
        this.shuffled = Arrays.copyOf(nums, nums.length);
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        for (int i = nums.length - 1; i >= 0; i--) {
            int j = random.nextInt(i + 1);

            var temp = shuffled[i];
            shuffled[i] = shuffled[j];
            shuffled[j] = temp;
        }
        return shuffled;
    }
}