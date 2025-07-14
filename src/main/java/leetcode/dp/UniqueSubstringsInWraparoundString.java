package leetcode.dp;

class UniqueSubstringsInWraparoundString {
    public static void main(String[] args) {
        var u = new UniqueSubstringsInWraparoundString();
        int zabxxxa = u.findSubstringInWraproundString("zabxxxzab");
        System.out.println(zabxxxa);
    }

    public int findSubstringInWraproundString(String s) {
        int[] dp = new int[26];

        int k = 1;
        dp[s.charAt(0) - 'a'] = 1;
        for (int i = 1; i < s.length(); i++) {
            int i1 = s.charAt(i) - 'a';
            int i2 = s.charAt(i - 1) - 'a';
            boolean isAdjacent = (i1 - i2 + 26) % 26 == 1;

            if (isAdjacent) {
                k++;
            } else {
                k = 1;
            }
            dp[i1] = Math.max(dp[i1], k);
        }

        var count = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > 0) {
                count += dp[i];
            }
        }
        return count;
    }
}
