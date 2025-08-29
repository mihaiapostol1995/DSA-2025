package leetcode.dp;

class BurstBalloons {

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;

        for (int i = 1; i <= n; i++) {
            balloons[i] = nums[i - 1];
        }

        int[][] dp = new int[n + 2][n + 2];
        // start with small len. bottom-up approach
        for (int len = 2; len <= n + 1; len++) {
            for (int left = 0; left + len <= n + 1; left++) {
                int right = left + len;
                // move with k between left and right.
                for (int k = left + 1; k < right; k++) {
                    dp[left][right] = Math.max(dp[left][right],
                            dp[left][k] + dp[k][right] + balloons[left] * balloons[k] * balloons[right]);
                }
            }
        }
        return dp[0][n + 1];

        /*
        simple example:
        3,4
        1, 3, 4, 1

        len = 2
        dp[0, 2], 1 iteration
        dp[1, 3], 1 iteration

        len = 3 (the end)
        finally, do dp[0, 3] which depends on the above, which has 2 iterations
        */
    }

    public static void main(String[] args) {
        var b = new BurstBalloons();
        int i = b.maxCoins(new int[]{1, 5});
        System.out.println(i);
    }
}
