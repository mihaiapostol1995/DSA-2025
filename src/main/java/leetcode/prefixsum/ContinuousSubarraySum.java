package leetcode.prefixsum;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ContinuousSubarraySum {
    public static void main(String[] args) {
        var c = new ContinuousSubarraySum();
        c.checkSubarraySum(new int[]{1,2,12}, 6);
    }

    // If two prefix sums have the same remainder when divided by k, then the subarray between them has a sum divisible by k.
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 1) {
            return false;
        }

        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sum %= k;
            if (sumToIndex.containsKey(sum)) {
                // for 5, 0, 0, 0, 0
                if (i - sumToIndex.get(sum) >= 2) {
                    return true;
                }
            } else {
                sumToIndex.put(sum, i);
            }
        }
        return false;
    }
}
