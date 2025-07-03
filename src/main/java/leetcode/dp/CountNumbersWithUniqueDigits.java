package leetcode.dp;

class CountNumbersWithUniqueDigits {
    public static void main(String[] args) {

    }

    public int countNumbersWithUniqueDigits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 10;

        int count = 9;
        int multiply = 9 * 9;
        for (int i = 2; i <= n; i++) {
            dp[i] = multiply + dp[i - 1];
            multiply = multiply * --count;
        }
        return dp[n];
    }
}
