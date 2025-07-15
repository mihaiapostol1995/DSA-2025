package leetcode.dp;

class GuessNumberHigherOrLowerII {

    // minimax, hard
    public static void main(String[] args) {
        GuessNumberHigherOrLowerII g = new GuessNumberHigherOrLowerII();
        g.getMoneyAmount(4);
    }

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];

        // len = length of the range we're considering. LITERALLY
        for (int len = 2; len <= n; len++) {
            // start < n - len + 1 => until MAX start
            for (int start = 1; start < n - len + 2; start++) {
                int end = start + len - 1; // have 2 length initially

                for (int i = start; i < end; i++) {
                    int cost = i + Math.max(dp[start][i - 1], dp[i + 1][end]); //WHYYYYYYYY
                    if (dp[start][end] > 0) {
                        dp[start][end] = Math.min(cost, dp[start][end]);
                    } else {
                        dp[start][end] = cost;
                    }
                }
            }
        }
        return dp[1][n];
    }
}