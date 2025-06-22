package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class DistinctLetters {
    public static void main(String[] args) {
        countDistinctLetters(List.of("aac", "abc", "a", "aca", "ab", "bba", "aba", "bbac"));
    }

    static void countDistinctLetters(List<String> input) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        // O(n)
        for (String word : input) {
            Set<String> uniqueLetters = new HashSet<>();
            // O(n)
            for (char c : word.toCharArray()) {
                uniqueLetters.add(String.valueOf(c));
            }

            String[] letters = uniqueLetters.toArray(new String[uniqueLetters.size()]);
            // O n(logn)
            Arrays.sort(letters);
            // O (n)
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < letters.length; i++) {
                builder.append(letters[i]);
            }

            map.put(builder.toString(), map.getOrDefault(builder.toString(), 0) + 1);
        }

        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
