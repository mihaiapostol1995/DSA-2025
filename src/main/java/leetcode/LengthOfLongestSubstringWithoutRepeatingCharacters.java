package leetcode;

import java.util.HashSet;

class LengthOfLongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        HashSet<Character> initialSet = new HashSet<>();
        int count = 0;
        int max = 0;
        int latestUniqueIndex = 1;
        for (int i = 0; i < s.length(); i++) {
            if (initialSet.contains(s.charAt(i))) {
                latestUniqueIndex = i - initialSet.size() + 1; // Find the index of the last unique character
                initialSet.clear();
                initialSet.add(s.charAt(latestUniqueIndex));
                max = Math.max(max, count);
                count = 1; // Reset count for the new substring
                i = latestUniqueIndex; // Reset i to the latest unique index
            } else {
                initialSet.add(s.charAt(i));
                count++;
            }
        }
        return Math.max(count, max);
    }
}
