package leetcode.string;

import java.util.HashSet;
import java.util.Set;

class OptimalPartitionOfString {
    public static void main(String[] args) {
        var opt = new OptimalPartitionOfString();
        int i = opt.partitionString("abacaba");
        System.out.println(i);
    }

    public int partitionString(String s) {
        Set<Character> set = new HashSet<>();

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            if (set.contains(ch)) {
                count++;
                set.clear();
                set.add(ch);
            } else {
                set.add(ch);
            }
        }
        return count + 1;
    }
}
