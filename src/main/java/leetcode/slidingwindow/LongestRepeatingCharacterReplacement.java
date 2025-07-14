package leetcode.slidingwindow;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        // AABABA
        var c = new LongestRepeatingCharacterReplacement();
        int i = c.characterReplacement("AABABBA", 1);
        System.out.println(i);
    }

    // HARD
    public int characterReplacement(String s, int k) {
        int max = Integer.MIN_VALUE;
        int maxCount = 0;
        int left = 0;
        Map<Character, Integer> frequency = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            frequency.put(rightChar, frequency.getOrDefault(rightChar, 0) + 1);

            maxCount = Math.max(maxCount, frequency.get(rightChar)); // MAX frequency
            // window size minus maxCount
            while (right - left - maxCount >= k) { // you can do just K changes
                frequency.put(s.charAt(left), frequency.getOrDefault(s.charAt(left), 0) - 1);
                left++;

                maxCount = Collections.max(frequency.values());
            }

            max = Math.max(max, right - left + 1); // +1 is for  the index
        }

        return max;
    }
}
