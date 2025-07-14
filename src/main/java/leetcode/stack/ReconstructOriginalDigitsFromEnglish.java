package leetcode.stack;

import java.util.List;

class ReconstructOriginalDigitsFromEnglish {
    public static void main(String[] args) {
        var r = new ReconstructOriginalDigitsFromEnglish();
//        r.originalDigits()
    }

    public String originalDigits(String s) {
        int[] letters = new int[26];
        for (char c : s.toCharArray()) {
            letters[c - 'a']++;
        }

        int[] digits = new int[9];
        digits[0] = letters['z' - 'a']; // zero
        digits[2] = letters['w' - 'a']; // two
        digits[4] = letters['u' - 'a']; // four
        digits[6] = letters['x' - 'a']; // six
        digits[8] = letters['g' - 'a']; // eight

        // Overlapping letters
        digits[3] = letters['h' - 'a'] - digits[8]; // three
        digits[5] = letters['f' - 'a'] - digits[4]; // five
        digits[7] = letters['s' - 'a'] - digits[6]; // seven
        digits[1] = letters['o' - 'a'] - digits[0] - digits[2] - digits[4]; // one
        digits[9] = letters['i' - 'a'] - digits[5] - digits[6] - digits[8]; // nine

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< digits.length; i++) {
            while (digits[i] != 0) {
                sb.append(i);
                digits[i]--;
            }
        }
        return sb.toString();
    }
}
