package leetcode.dp;

class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
        l.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 0;

        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // NOT just smaller but also INCREASING
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
