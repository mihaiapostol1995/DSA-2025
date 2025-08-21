package leetcode;

import java.util.Map;

class StringToIntegerAtoi {
    public static void main(String[] args) {
        System.out.println(myAtoi("-1010023630o4"));
    }

    public static int myAtoi(String s) {
        Map<Character, Integer> nums = Map.of('0',  0,
                '1', 1,
                '2', 2,
                '3', 3,
                '4', 4,
                '5', 5,
                '6', 6,
                '7', 7,
                '8', 8,
                '9', 9);
        int result = 0;
        boolean isNegative = false;

        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                index++;
            } else {
                break;
            }
        }

        for (int i = index; i < s.length(); i++) {
            if (s.charAt(i) == '-') {
                index++;
                isNegative = true;
                break;
            } else if (s.charAt(i) == '+') {
                index++;
                break;
            } else {
                break;
            }
        }

        return number(s, index, nums, result, isNegative);
    }

    private static int number(String s, int index, Map<Character, Integer> nums, int result, boolean isNegative) {
        int signedResult = 0;
        for (int i = index; i < s.length(); i++) {
            if (!nums.containsKey(s.charAt(i))) {
                return signedResult;
            }
            if (signedResult > Integer.MAX_VALUE / 10
                    || (signedResult == Integer.MAX_VALUE /10 && nums.get(s.charAt(i)) > 7)) {
                return Integer.MAX_VALUE;
            } else if (signedResult < Integer.MIN_VALUE / 10
                    || (signedResult == Integer.MIN_VALUE /10 && nums.get(s.charAt(i)) > 8)) {
                return Integer.MIN_VALUE;
            } else {
                result = result * 10 + nums.get(s.charAt(i));
                signedResult = result * (isNegative ? -1 : 1);
            }
        }
        return signedResult;
    }
}
