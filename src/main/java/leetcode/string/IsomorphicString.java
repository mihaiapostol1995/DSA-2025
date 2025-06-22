package leetcode.string;

import java.util.HashMap;
import java.util.Map;

class IsomorphicString {

    public static void main(String[] args) {
        isIsomorphic("foo", "bar");
    }

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<String, String> tToSMap = new HashMap<>();
        Map<String, String> sToTMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            var comparedString = t.substring(i, i + 1);

            if (tToSMap.containsKey(comparedString)) {
                String s1 = tToSMap.get(comparedString);
                if (!s1.equals(s.substring(i, i + 1))) {
                    return false;
                }
            } else {
                tToSMap.put(comparedString, s.substring(i, i + 1));
            }

            var secondComparedString = s.substring(i, i + 1);
            if (sToTMap.containsKey(secondComparedString)) {
                String s1 = sToTMap.get(secondComparedString);
                if (!s1.equals(t.substring(i, i + 1))) {
                    return false;
                }
            } else {
                sToTMap.put(secondComparedString, t.substring(i, i + 1));
            }
        }

        return true;
    }
}
