package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();

        Arrays.sort(nums);  // Critical step
        int n = nums.length;

        // dp[i] = length of longest divisible subset ending at index i
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        // parent[i] = previous index in the optimal subset ending at i
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int maxLen = 1;
        int maxIdx = 0;

        // Fill DP table
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If nums[i] is divisible by nums[j], we can extend the chain
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }

            // WTF...
            // Track the index with maximum length
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIdx = i;
            }
        }

        // another WTF
        // Reconstruct the subset by following parent pointers
        List<Integer> result = new ArrayList<>();
        int curr = maxIdx;
        while (curr != -1) {
            result.add(nums[curr]);
            curr = parent[curr];
        }

        return result;
    }
}
