package leetcode.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class ReverseWordsInAString {
    public static void main(String[] args) {
        reverseWords("the sky is blue");
    }

    static String reverseWords(String s) {
        Stack<String> stringStack = new Stack<>();

        var word = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (word.isEmpty()) {
                    continue;
                } else {
                    stringStack.add(word.toString());
                    word.delete(0, word.length());
                }
            } else {
                word.append(s.charAt(i));
            }
        }
        if (!word.isEmpty()) {
            stringStack.add(word.toString());
        }

        StringBuilder builder = new StringBuilder();
        while (!stringStack.isEmpty()) {
            builder.append(stringStack.pop());
            builder.append(" ");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }
}
