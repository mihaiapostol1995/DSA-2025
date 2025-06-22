package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

class RepeatedDNASequences {
    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length() - 10; i++) {
            if (map.containsKey(s.substring(i, i + 10))) {
                map.put(s.substring(i, i + 10), map.get(s.substring(i, i + 10)) + 1);
            } else {
                map.put(s.substring(i, i + 10), 1);
            }
        }

        return map.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }
}
