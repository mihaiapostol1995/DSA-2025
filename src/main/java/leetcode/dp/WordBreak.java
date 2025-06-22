package leetcode.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class WordBreak {

    public static void main(String[] args) {
        wordBreak("leetcode", Arrays.asList("leet", "code"));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        set.addAll(wordDict);

        boolean[] dp = new boolean[s.length() + 1]; // ALWAYS need the last case
        dp[0] = true; // ALWAYS need the base case
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}
