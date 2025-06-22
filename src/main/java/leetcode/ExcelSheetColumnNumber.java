package leetcode;

import java.util.HashMap;
import java.util.Map;

class ExcelSheetColumnNumber {

    public static void main(String[] args) {
        titleToNumber("AZ");
    }

    public static int titleToNumber(String columnTitle) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            char letter = (char) ('A' + i);
            map.put(String.valueOf(letter), i + 1);
        }

        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            char c = columnTitle.charAt(i);
            result = result * 26 + map.get(String.valueOf(c));
        }

        return result;
    }
}
