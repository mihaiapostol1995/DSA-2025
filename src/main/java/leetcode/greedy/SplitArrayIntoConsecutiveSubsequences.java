package leetcode.greedy;

import java.util.HashMap;
import java.util.Map;

class SplitArrayIntoConsecutiveSubsequences {

    // hard intuition
    // TODO: redo
    public static void main(String[] args) {
        var s = new  SplitArrayIntoConsecutiveSubsequences();
        //1,2,3,3,4,4,5,5
        //1,2,3,3,4,5
        s.isPossible(new int[]{1,2,3,3,4,4,5,5});
    }

    // Intuition: track the frequency, then exhaust from the tail till the end
    // NO while, take one number at a time
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        Map<Integer, Integer> tailMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (freq.getOrDefault(nums[i], 0) == 0) {
                continue;
            }

            // first take care of the tail
            int key = nums[i] - 1;
            if (tailMap.getOrDefault(key, 0) > 0) { // duplicates only!
                freq.put(nums[i], freq.get(nums[i]) - 1);

                tailMap.put(key, tailMap.getOrDefault(key, 0) - 1);
                tailMap.put(nums[i], tailMap.getOrDefault(nums[i], 0) + 1);
            } else if (freq.getOrDefault(nums[i], 0) > 0
                    && freq.getOrDefault(nums[i] + 1, 0) > 0
                    && freq.getOrDefault(nums[i] + 2, 0) > 0) {
                freq.put(nums[i] + 2, freq.get(nums[i] + 2) - 1);
                freq.put(nums[i] + 1, freq.get(nums[i] + 1) - 1);
                freq.put(nums[i], freq.get(nums[i]) - 1);

                tailMap.put(nums[i] + 2, tailMap.getOrDefault(nums[i] + 2, 0) + 1);
            } else {
                return false;
            }
        }

        return true;
    }
}
