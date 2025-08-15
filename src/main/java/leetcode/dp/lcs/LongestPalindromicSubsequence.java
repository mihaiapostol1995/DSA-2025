package leetcode.dp.lcs;

class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        var l = new  LongestPalindromicSubsequence();
        int i = l.longestPalindromeSubseq("abcbfa");
        System.out.println(i);
    }

    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        // fill in the initial array
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }

        for (int len = 1; len < s.length(); len++) {
            for (int i = 0; i < s.length() - len; i++) {
                int j = i + len;
                // start with 0, 1
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]); // since palindromes can be both "aaa" and "aa"
                }
            }
        }

        return dp[0][s.length() - 1];
    }
}
