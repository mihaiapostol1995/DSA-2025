package leetcode.slidingwindow;

// GOOD problem for sliding window!
class MinimumWindowSubstring {

    public static void main(String[] args) {
        var s = new MinimumWindowSubstring();
        s.minWindow("ab", "b");
    }


    public String minWindow(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        String result = "";
        int start = 0;
        for (int right = 0; right < s.length(); right++) {
            String window = s.substring(start, right + 1);
            if (containsAllChars(window, t)) {
                if (window.length() < minLen) {
                    minLen = window.length();
                    result = window;
                }
                // reset
                right = start;
                start++;
            }
        }
        return result;
    }

    private boolean containsAllChars(String window, String t) {
        int[] count = new int[128];
        for (char c : window.toCharArray()) count[c]++;
        for (char c : t.toCharArray()) {
            if (count[c] == 0) return false;
            count[c]--;
        }
        return true;
    }
}