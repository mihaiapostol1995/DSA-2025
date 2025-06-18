package leetcode.array.subsets;

import java.util.ArrayList;
import java.util.List;

class WordSubsets {
    public static void main(String[] args) {
        String[] words1 = {"amazon", "apple", "facebook", "google", "leetcode"};
        String[] words2 = {"e", "o"};
        wordSubsets(words1, words2);
    }

    public static List<String> wordSubsets(String[] words1, String[] words2) {
        int[] wordCount = new int[26];
        for (int i = 0; i < words2.length; i++) {
            int[] chars = countChars(words2[i]);

            for (int j = 0; j < chars.length; j++) {
                wordCount[j] = Math.max(wordCount[j], chars[j]);
            }
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < words1.length; i++) {
            var word = words1[i];
            int[] wordCount2 = countChars(word);

            boolean add = true;
            for (int j = 0; j < wordCount.length; j++) {
                if (wordCount[j] > wordCount2[j]) {
                    add = false;
                    break;
                }
            }
            if (add) {
                result.add(word);
            }
        }

        return result;
    }

    private static int[] countChars(String word) {
        int[] count = new int[26];
        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }
        return count;
    }
}
