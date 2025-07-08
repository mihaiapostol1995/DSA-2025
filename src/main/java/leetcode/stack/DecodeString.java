package leetcode.stack;

import java.util.Stack;

class DecodeString {

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
//        String s = decodeString.decodeString("3[a2[c]]");
        String s = decodeString.decodeString("3[a]2[b]");
        System.out.println(s);
    }

    public String decodeString(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        Stack<Integer> freq = new Stack<>();

        StringBuilder currentStr = new StringBuilder();
        int digit = 0;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                digit = digit * 10 + (s.charAt(i) - '0');
            } else if (s.charAt(i) == '[') {
                freq.push(digit);
                stack.push(currentStr); // THIS IS THE FIX
                // reset everything now
                digit = 0;
                currentStr = new StringBuilder();
            } else if (s.charAt(i) == ']') {
                // Pop previous string and repeat count
                StringBuilder prevString = stack.pop();
                int count = freq.pop();

                // Repeat current string and prepend previous
                for (int j = 0; j < count; j++) {
                    prevString.append(currentStr);
                }
                currentStr = prevString;
            } else {
                currentStr.append(s.charAt(i));
            }
        }
        return currentStr.toString();
    }
}
