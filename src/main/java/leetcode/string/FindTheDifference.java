package leetcode.string;

class FindTheDifference {

    public static void main(String[] args) {
        FindTheDifference findTheDifference = new FindTheDifference();
        char theDifference = findTheDifference.findTheDifference("abcd", "bacde");
        System.out.println(theDifference);
    }

    public char findTheDifference(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            alphabet[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (alphabet[i] != 0) {
                return (char) (i + 'a');
            }
        }
        return '-';
    }
}
