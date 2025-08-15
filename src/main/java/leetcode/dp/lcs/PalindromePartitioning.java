package leetcode.dp.lcs;

import java.util.ArrayList;
import java.util.List;

class PalindromePartitioning {

    public static void main(String[] args) {
        var p = new PalindromePartitioning();
        p.partition("aab");
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        partition(s, new ArrayList<>(), result);
        return result;
    }

    public void partition(String s, List<String> current, List<List<String>> result) {
        if (s.isEmpty()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            String firstHalf = s.substring(0, i + 1);
            if (isPalindrome(firstHalf)) {
                current.add(firstHalf);

                String secondHalf = s.substring(i + 1);
                partition(secondHalf, current, result);

                current.removeLast();
            }
        }
    }

    boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }

        return true;
    }
}
