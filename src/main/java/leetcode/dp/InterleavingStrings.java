package leetcode.dp;

class InterleavingStrings {
    public static void main(String[] args) {
        var i = new  InterleavingStrings();
        boolean interleave = i.isInterleave("aabcc", "dbbca", "aadbbcbcac");
        System.out.println(interleave);
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        boolean[] used = new boolean[s3.length()];

        // Try to mark positions of s1
        int i1 = 0;
        for (int i = 0; i < s3.length() && i1 < s1.length(); i++) {
            if (s3.charAt(i) == s1.charAt(i1)) {
                used[i] = true;
                i1++;
            }
        }

        // Try to mark positions of s2
        int i2 = 0;
        for (int i = 0; i < s3.length() && i2 < s2.length(); i++) {
            if (!used[i] && s3.charAt(i) == s2.charAt(i2)) {
                used[i] = true;
                i2++;
            }
        }

        // Check if all positions were filled
        for (boolean b : used) {
            if (!b) return false;
        }
        return true;
    }
}
