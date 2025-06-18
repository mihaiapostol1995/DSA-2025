package leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Map<Character, String> phoneMap = new HashMap<>();
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");

        var list = new ArrayList<String>();
        letterCombinations("23", 0, new StringBuilder(), list, phoneMap);
    }

    public static void letterCombinations(String digits, int target, StringBuilder sb, List<String> result,
                                          Map<Character, String> phoneMap) {
        if (sb.length() == digits.length() && !digits.isEmpty()) {
            result.add(sb.toString());
            return;
        }

        char number = digits.charAt(target);
        String s = phoneMap.get(number);
        for (char c : s.toCharArray()) {
            sb.append(c);
            letterCombinations(digits, target + 1, sb, result, phoneMap);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
