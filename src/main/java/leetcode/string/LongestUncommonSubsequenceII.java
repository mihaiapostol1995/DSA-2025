package leetcode.string;

import java.util.Arrays;
import java.util.Comparator;

public class LongestUncommonSubsequenceII {
    public static void main(String[] args) {
        var l = new LongestUncommonSubsequenceII();
        int luSlength = l.findLUSlength(new String[]{"aaa", "aaa", "aa"});
        System.out.println(luSlength);
    }

    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (a, b) -> b.length() - a.length());

        int maxLength = -1;

        for (int i = 0; i < strs.length; i++) {
            var str = strs[i];
            boolean isSubsequence = false;
            for (int j = 0; j < strs.length; j++) {
                var str1 = strs[j];
                if (i == j) {
                    continue;
                } else if (isSubsequence(str, str1)) {
                    isSubsequence = true;
                    break;
                }
            }
            if (!isSubsequence) {
                return str.length();
            }
        }
        return maxLength;
    }

    private boolean isSubsequence(String a, String b) {
        int left = 0;
        for (int right = 0; right < b.length(); right++) {
            if (left == a.length()) {
                return true;
            }
            if (a.charAt(left) == b.charAt(right)) {
                left++;
            }
        }

        return left == a.length();
    }
}
