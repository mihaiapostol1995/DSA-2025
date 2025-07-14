package leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FindAllAnagramsInAString {
    public static void main(String[] args) {
        var f = new FindAllAnagramsInAString();
        List<Integer> anagrams = f.findAnagrams("cbaebabacd", "abc");
        System.out.println(anagrams);
    }

    public List<Integer> findAnagrams(String s, String p) {
        int[] frequency = new int[26];
        for (int i = 0; i < p.length(); i++) {
            frequency[p.charAt(i) - 'a']++;
        }

        int left = 0, right = p.length();

        List<Integer> ans = new ArrayList<>();

        while (right < s.length()) {
            int[] freq = new int[26];
            for (int i = left; i < right; i++) {
                freq[s.charAt(i) - 'a']++;
            }

            if (Arrays.equals(freq, frequency)) {
                ans.add(left);
            }
            left++;
            right++;
        }

        return ans;
    }
}
