package leetcode.array;

import java.util.HashMap;
import java.util.Map;

class WordPattern {
    public static void main(String[] args) {
        WordPattern wp = new WordPattern();
        wp.wordPattern("abba", "dad cat cat dad");
    }

    public boolean wordPattern(String pattern, String s) {
        String[] word = s.split("\\s+");
        if (pattern.length() != word.length) return false;

        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            var cur = pattern.charAt(i);
            if (map.containsKey(cur)) {
                var old = map.get(cur);
                if (!old.equals(word[i])) {
                    return false;
                }
            } else {
                map.put(cur, word[i]);
            }
        }
        return true;
    }
}
