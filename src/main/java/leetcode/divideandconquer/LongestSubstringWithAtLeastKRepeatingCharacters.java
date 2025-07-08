package leetcode.divideandconquer;

class LongestSubstringWithAtLeastKRepeatingCharacters {
    public static void main(String[] args) {
        var ls = new LongestSubstringWithAtLeastKRepeatingCharacters();
        int i = ls.longestSubstring("weitong", 2);
        System.out.println(i);
    }

    public int longestSubstring(String s, int k) {
        if (s.length() < k) return 0;

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] < k) {
                return Math.max(longestSubstring(s.substring(0, i), k),
                        longestSubstring(s.substring(i + 1), k));
            }
        }

        return s.length();
    }
}
