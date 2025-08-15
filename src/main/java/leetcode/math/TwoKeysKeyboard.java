package leetcode.math;

class TwoKeysKeyboard {

    public static void main(String[] args) {
        var t = new TwoKeysKeyboard();
        t.minSteps(9);
    }

    public int minSteps(int n) {
        // this problem is about finding the FACTORS!
        int[] dp = new int[n + 1];
        // Base case: 1 'A' needs 0 steps
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = i; // Worst case: Paste 1 'A' i-1 times
            for (int j = 1; j < i / 2; j++) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j); // if you have 18, you do 9 and 2, not 2 and 9.... the LARGEST one first
                }
            }
        }

        return dp[n];
    }
}
