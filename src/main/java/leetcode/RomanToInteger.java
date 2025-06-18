package leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(intToRoman(3249));
    }


    public static String intToRoman(int num) {
        // Using LinkedHashMap to maintain insertion order (descending values)
        Map<Integer, String> valueToSymbol = new LinkedHashMap<>();

        // Add mappings in descending order
        // Include subtractive cases (4, 9, 40, 90, 400, 900)
        valueToSymbol.put(1000, "M");
        valueToSymbol.put(900, "CM");
        valueToSymbol.put(500, "D");
        valueToSymbol.put(400, "CD");
        valueToSymbol.put(100, "C");
        valueToSymbol.put(90, "XC");
        valueToSymbol.put(50, "L");
        valueToSymbol.put(40, "XL");
        valueToSymbol.put(10, "X");
        valueToSymbol.put(9, "IX");
        valueToSymbol.put(5, "V");
        valueToSymbol.put(4, "IV");
        valueToSymbol.put(1, "I");

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : valueToSymbol.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            while (num >= key) {
                num -= key;
                sb.append(value);
            }
        }
        return sb.toString();
    }
}
