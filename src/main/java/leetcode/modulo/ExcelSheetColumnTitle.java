package leetcode.modulo;

import java.util.HashMap;
import java.util.Map;

class ExcelSheetColumnTitle {

    public static void main(String[] args) {
        String s = convertToTitle(28);
        System.out.println(s);
    }

    public static String convertToTitle(int columnNumber) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(i, String.valueOf((char) ('A' + i)));
        }

        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            int digit = columnNumber % 26;
            sb.append(map.get(digit));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }
}