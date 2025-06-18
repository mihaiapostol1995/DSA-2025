package leetcode;

class LongestCommonPrefix {
    public static void main(String[] args) {
        longestCommonPrefix(new String[]{"aaa", "aa", "aaa"});
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            var newPrefix = "";
            for (int j = 0; j < strs[i].length(); j++) {
                if (prefix.length() > j) {
                    if (prefix.charAt(j) != strs[i].charAt(j)) {
                        break;
                    } else {
                        newPrefix = prefix.substring(0, j + 1);
                    }
                } else {
                    break;
                }
            }

            prefix = newPrefix;
        }

        return prefix;
    }
}
