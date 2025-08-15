package leetcode;

class LongestPalindrome {
    // TODO: redo this... it must be done easier...
    public static void main(String[] args) {
        System.out.println(longestPalindrome("abaaba"));
    }

    private static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String longestPalindrome = s.charAt(0) + "";
        for (int i = 1; i < s.length(); i++) {

            int j = 1;
            // branch out
            while (i - j >= 0 && i + j < s.length()) {
                if (s.charAt(i - j) == s.charAt(i + j)) {
                    String candidate = s.substring(i - j, i + j + 1);
                    if (candidate.length() > longestPalindrome.length()) {
                        longestPalindrome = candidate;
                    }
                    j++;
                } else {
                    break;
                }
            }

            // I think this is for odd
            int k = i - 1;
            int l = 0;
            while (k - l >= 0 && i + l < s.length()) {
                if (s.charAt(k - l) == s.charAt(i + l)) {
                    String candidate = s.substring(k - l, i + l + 1);
                    if (candidate.length() > longestPalindrome.length()) {
                        longestPalindrome = candidate;
                    }
                    l++;
                } else {
                    break;
                }
            }
        }
        return longestPalindrome;
    }


    // much easier:
    private static String longestPalindromeFast(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int start = 0, maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // Odd-length palindrome
            int len2 = expandAroundCenter(s, i, i + 1); // Even-length palindrome
            int len = Math.max(len1, len2);

            if (len > maxLength) {
                maxLength = len;
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLength);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
