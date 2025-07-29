package leetcode.dp.lcs;

class DeleteOperationForTwoStrings {

    public static void main(String[] args) {
        var d = new DeleteOperationForTwoStrings();
        int i = d.minDistance("horse", "ros");
        System.out.println(i);
    }

    // longest common substring
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // simple example, abc and abc
                    // i-1 and j would not work: eet, et. for this same reason j-i and i would not work, the order should not matter!
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int common = dp[word1.length()][word2.length()];

//        return common;

        return word1.length() - common + word2.length() - common;
    }
}
