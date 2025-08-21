package leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FindCommonCharacters {
    public static void main(String[] args) {
        var f= new  FindCommonCharacters();
        List<String> strings = f.commonChars(new String[]{"acabcddd","bcbdbcbd","baddbadb","cbdddcac",
                "aacbcccd","ccccddda","cababaab","addcaccd"});
        System.out.println(strings);
    }

    public List<String> commonChars(String[] words) {
        Map<Character, Integer> previousMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            Map<Character, Integer> map = new HashMap<>();
            var word = words[i];
            for (int j = 0; j < word.length(); j++) {
                map.put(word.charAt(j), map.getOrDefault(word.charAt(j), 0) + 1);
            }
            if (previousMap.isEmpty() && i > 0) {
                return List.of();
            }
            previousMap = compareMaps(previousMap, map);
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : previousMap.entrySet()) {
            var count = entry.getValue();
            for (int i = 0; i < count; i++) {
                result.add(entry.getKey().toString());
            }
        }
        return result;
    }

    private Map<Character, Integer> compareMaps(Map<Character, Integer> previousMap,
                                                Map<Character, Integer> map) {
        if (previousMap.isEmpty()) {
            return map;
        }

        Map<Character, Integer> result = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : previousMap.entrySet()) {
            if (map.containsKey(entry.getKey())) {
                var previousMapCount = map.get(entry.getKey());
                result.put(entry.getKey(), Math.min(previousMapCount, entry.getValue()));
            }
        }
        return result;
    }
}
