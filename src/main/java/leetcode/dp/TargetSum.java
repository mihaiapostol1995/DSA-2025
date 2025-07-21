package leetcode.dp;

public class TargetSum {
    public static void main(String[] args) {
        var t = new TargetSum();
        t.findTargetSumWays(new int[]{1,2,3,4,5}, 3);
    }

    public int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        // Check if transformation is valid
        if ((target + total) % 2 != 0 || Math.abs(target) > total) {
            return 0;
        }

        int subsetSum = (target + total) / 2;
        int n = nums.length;

        // dp[i][s] = number of ways to reach sum s using first i elements
        int[][] dp = new int[n + 1][subsetSum + 1];
        dp[0][0] = 1; // One way to make sum 0 with 0 elements

        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int s = 0; s <= subsetSum; s++) {
                dp[i][s] = dp[i - 1][s]; // Don't include num
                // check boundary
                if (s >= num) {
                    dp[i][s] += dp[i - 1][s - num]; // Include num
                }
            }
        }

        return dp[n][subsetSum];
    }

    public int findTargetSumWaysDfs(int[] nums, int target) {
       return findTargetSumWaysDfsHelper(nums, target, 0, 0);
    }

    public int findTargetSumWaysDfsHelper(int[] nums, int target, int currentSum, int index) {
        if (index == nums.length && currentSum == target) {
                return 1;
        }

        return findTargetSumWaysDfsHelper(nums, target, currentSum + nums[index], index + 1)
                + findTargetSumWaysDfsHelper(nums, target, currentSum - nums[index], index + 1);
    }
}
