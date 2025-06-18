package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

class RestoreIpAddresses {

    public static void main(String[] args) {
        var results = new ArrayList<String>();
        restoreIpAddresses("0000", 0, 0, new ArrayList<>(), results);
        System.out.println(results);
    }

    public static void restoreIpAddresses(String s, int index, int dots, List<String> list, List<String> results) {
        if (dots == 4) {
            if (index == s.length()) {
                results.add(String.join(".", list));
            }
            return;
        }

        dots++;
        for (int i = index; i < Math.min(index + 3, s.length()); i++) {
            var substring = s.substring(index, i + 1);
            if (substring.length() > 1 && substring.charAt(0) == '0') {
                continue; // Skip invalid segments with leading zeros
            }

            if (Integer.parseInt(substring) <= 255) {
                list.add(substring);
                restoreIpAddresses(s, i + 1, dots, list, results);
                list.remove(list.size() - 1);
            }
        }
    }
}
