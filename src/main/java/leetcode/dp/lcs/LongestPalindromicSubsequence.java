package leetcode.dp.lcs;

class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        var l = new  LongestPalindromicSubsequence();
        // "abcbfa"
        int i = l.longestPalindromeSubseq("abba");
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
                    dp[i][j] = Math.max(dp[i][j - 1],
                            dp[i + 1][j]); // since palindromes can be both "aaa" and "aa"
                }
            }
        }

//        for (int i = s.length() - 2; i >= 0; i--) {
//            for (int j = i + 1; j < s.length(); j++) {
//                int toAdd = 0;
//                if (s.charAt(i) == s.charAt(j)) {
//                    toAdd = 2;
//                    if (j - i == 1) {
//                        dp[i][j] = 2;
//                        continue;
//                    }
//                }
//                dp[i][j] = Math.max(dp[i][j - 1] + toAdd,
//                        dp[i + 1][j] + toAdd);
//            }
//        }

        return dp[0][s.length() - 1];
    }
}
