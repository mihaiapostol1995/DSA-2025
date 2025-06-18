package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

class DecodeWays {

    public static void main(String[] args) {
        System.out.println(numDecodings("10"));
    }

    public static int numDecodings(String s) {
        Map<String, Character> letterMap = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            letterMap.put(String.valueOf(i), (char) ('A' + (i - 1)));
        }

        int maxWays = 0;
        int[] dp = new int[s.length()];
        if (letterMap.containsKey(s.substring(0, 1))) {
            dp[0] = 1;
            maxWays++;
        } else {
            dp[0] = 0;
        }
        if (s.length() == 1) {
            return maxWays;
        }

        if (letterMap.containsKey(s.substring(0, 2))
                && !s.substring(1, 2).equals("0")) {
            dp[1] = dp[0] + 1;
            maxWays++;
        } else {
            dp[1] = 1;
        }

        for (int i = 2; i < s.length(); i++) {
            boolean b1 = letterMap.containsKey(String.valueOf(s.charAt(i)));
            int temp1 = 0;
            if (b1) {
                maxWays = Math.max(maxWays, dp[i - 1] + 1);
                temp1 = dp[i - 1] + 1;
            }

            int temp2 = 0;
            boolean b2 = letterMap.containsKey(s.substring(i - 1, i + 1));
            if (b2) {
                maxWays = Math.max(maxWays, dp[i - 2] + 1);
                temp2 = dp[i - 2] + 1;
            }
            dp[i] = Math.max(dp[i - 1], Math.max(temp1, temp2));
        }

        return maxWays;
    }

    static int numDecodings2(String s) {
        if (s == null || s.isEmpty()
                || (s.length() == 1 && s.charAt(0) == '0')) {
            return 0;
        }

        int size = s.length() + 1;
        int[] dp = new int[size];

        dp[0] = 1; // empty string
        dp[1] = 1;

        for (int i = 2; i < size; i++) {
            int current = s.charAt(i - 1);
            int prev = s.charAt(i - 2);

            if (current >= '1' && current <= '9') {
                dp[i] = dp[i - 1];
            }

            if (prev == '1' || (prev == '2' && current >= '0' && current <= '6')) {
                dp[i] += dp[i - 2];
            }

            if (dp[i] == 0) {
                return 0;
            }
        }

        return dp[size - 1];
    }
}
