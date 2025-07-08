package leetcode.string;

class IsSubsequence {
    public static void main(String[] args) {
        IsSubsequence isSubsequence = new IsSubsequence();
        isSubsequence.isSubsequence("ace", "abcde");
    }

    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return false;
        int leftS = 0;

        for (char c : t.toCharArray()) {
            if (s.charAt(leftS) == c) {
                leftS++;
            }
            if (leftS == s.length()) {
                return true;
            }
        }
        return false;
    }
}
