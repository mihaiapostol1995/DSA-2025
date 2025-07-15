package leetcode.dp;

class OnesAndZeroes {
    public static void main(String[] args) {
        var o = new OnesAndZeroes();
        o.findMaxForm(new String[]{"10", "1", "0"}, 3, 3);
    }
    // another HARD

//    Another Example Where This Matters
//    Strings: ["0", "1", "01"], m=1, n=1
//
//    After processing "0" and "1": dp[1][1] = 2
//    When processing "01" (1 zero, 1 one):
//
//    Without Math.max: dp[1][1] = dp[0][0] + 1 = 1 ❌
//    With Math.max: dp[1][1] = Math.max(2, 1) = 2 ✅.
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (int o = 0; o < strs.length; o++) {
            var string = strs[o];
            var ones = 0;
            var zeroes = 0;
            for (int j = 0; j < string.length(); j++) {
                if (string.charAt(j) == '1') {
                    ones++;
                } else if (string.charAt(j) == '0') {
                    zeroes++;
                }
            }

            // start from the end
            for (int i = m; i >= zeroes; i--) {
                for (int j = n; j >= ones; j--) {
                    int i1 = dp[i - zeroes][j - ones] + 1;
                    if (dp[i][j] < i1) {
                        dp[i][j] = i1;
                    } else {
                        dp[i][j] = dp[i][j];
                    }
                }
            }

        }

        return dp[m][n];
    }
}
