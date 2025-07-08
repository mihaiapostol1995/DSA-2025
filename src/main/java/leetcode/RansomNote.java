package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class RansomNote {
    public static void main(String[] args) {

    }

    public boolean canConstruct(String ransomNote, String magazine) {
        char[] chars = ransomNote.toCharArray();
        char[] magazineChars = magazine.toCharArray();

        Arrays.sort(chars);
        Arrays.sort(magazineChars);

        Queue<Character> queue = new LinkedList<>();
        for (char c : chars) {
            queue.add(c);
        }
        if (queue.isEmpty()) {
            return true;
        }

        for (char c : magazineChars) {
            if (queue.isEmpty()) {
                return true;
            }
            if (queue.peek().equals(c)) {
                queue.poll();
            }
        }
        return queue.isEmpty();
    }

    // OR AGAIN, COUNT THE FUCKING LETTERS!!!!!!!
}
