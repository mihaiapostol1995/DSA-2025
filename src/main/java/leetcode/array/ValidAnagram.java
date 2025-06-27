package leetcode.array;

import java.util.Arrays;

class ValidAnagram {
    public static void main(String[] args) {

    }

    public boolean isAnagram(String s, String t) {
        var s1 = s.toCharArray();
        Arrays.sort(s1);
        var t1 = t.toCharArray();
        Arrays.sort(t1);
        return Arrays.equals(s1, t1);
    }
}
