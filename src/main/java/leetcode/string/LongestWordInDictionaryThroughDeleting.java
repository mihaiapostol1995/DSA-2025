package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LongestWordInDictionaryThroughDeleting {

    public static void main(String[] args) {
        var l = new  LongestWordInDictionaryThroughDeleting();
        List<String> ale = new ArrayList<>(List.of("ale", "apple", "monkey", "plea"));
        l.findLongestWord("abpcplea", ale);
    }

    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((a, b) -> {
            if (a.length() == b.length()) {
                return a.compareTo(b);
            } else {
                return b.length() - a.length();
            }
        });

        for (var word : dictionary) {
            if (isSubsequence(s, word)) {
                return word;
            }
        }
        return "";
    }

    private boolean isSubsequence(String s1, String s2) {
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            i++;
        }

        return j == s2.length();
    }
}
