package leetcode.dp;

import java.util.Arrays;

class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        var pr = new  PartitionEqualSubsetSum();
        boolean b = pr.canPartition(new int[]{14,9,8,4,3,2});
        System.out.println(b);
    }

    // we are just finding the x/4 number
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];

        // A FUCKED UP hard PROBLEM. knapsack
        dp[0] = true;
        for (var num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num]; // DON'T reset it
                if (i == target && dp[i]) return true;
            }
        }


        return false;
    }
}
