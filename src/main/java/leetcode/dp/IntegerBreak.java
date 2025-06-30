package leetcode.dp;

class IntegerBreak {
    public static void main(String[] args) {
        IntegerBreak obj = new IntegerBreak();
        obj.integerBreak(10);
    }

    public int integerBreak(int n) {
        if (n == 1) return 1;
        if (n == 2) return 1;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max((i - j), dp[i - j]));
            }
        }
        return dp[n];
    }
}
