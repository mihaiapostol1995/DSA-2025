package leetcode.array;

import java.util.HashSet;
import java.util.Set;

class ReverserVowels {
    public static void main(String[] args) {
        ReverserVowels r = new ReverserVowels();
        String s = r.reverseVowels("IceCreAm");
        System.out.println(s);
    }

    public String reverseVowels(String s) {
        Set<Character> vowels = Set.of('a',  'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        int left = 0;
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left < right) {
            while (left < right && !vowels.contains(s.charAt(left))) {
                left++;
            }
            while (left < right && !vowels.contains(s.charAt(right))) {
                right--;
            }
            var temp = s.charAt(left);
            chars[left] = s.charAt(right);
            chars[right] = temp;
            left++;
            right--;
        }
        return String.valueOf(chars);
    }
}
