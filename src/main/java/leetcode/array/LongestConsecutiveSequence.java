package leetcode.array;

import java.util.Collections;
import java.util.TreeMap;

class LongestConsecutiveSequence {
    public static void main(String[] args) {
        var l = new LongestConsecutiveSequence();
        int i = l.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        System.out.println(i);
    }

    public int longestConsecutive(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, 0);
        }

        for (var entry : map.entrySet()) {
            int first = entry.getKey();
            if (map.containsKey(first - 1)) {
                map.put(first, map.get(first - 1) + 1);
            }
        }

        return Collections.max(map.values()) + 1;
    }
}
