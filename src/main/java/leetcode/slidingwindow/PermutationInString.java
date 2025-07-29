package leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

class PermutationInString {
    public static void main(String[] args) {
        var p = new  PermutationInString();
        boolean b = p.checkInclusion("adc", "dcda");
        System.out.println(b);
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
        }

        Map<Character, Integer> subMap = new HashMap<>();
        int globalCount = 0;
        int left = 0; // for sliding window

        for (int i = 0; i < s2.length(); i++) {
            var nextChar = s2.charAt(i);
            if (map.containsKey(nextChar)) {
                var count = map.get(nextChar);
                if (count <= subMap.getOrDefault(nextChar, 0)) {
                    // nothing found, reset
                    subMap.clear();
                    globalCount = 0;
                    i = left;
                } else {
                    subMap.put(s2.charAt(i), subMap.getOrDefault(nextChar, 0) + 1);
                    globalCount++;
                    if (globalCount == s1.length()) {
                        return true;
                    }
                }
            } else {
                // nothing found, reset
                subMap.clear();
                globalCount = 0;
                i = left;
            }
        }
        return false;
    }
}
