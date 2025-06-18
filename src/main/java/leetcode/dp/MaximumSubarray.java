package leetcode.dp;

class MaximumSubarray {
    public static void main(String[] args) {
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maximumSubarray(array);
    }

    static int maximumSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
             dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
             if (dp[i] > max) {
                 max = dp[i];
             }
        }

        return max;
    }
}
