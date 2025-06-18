package leetcode.stack;

import java.util.Stack;

class ValidParentheses {

    public static void main(String[] args) {
        isValid("([])");
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                var popped = stack.pop();
                if (popped == '[' && ']' != c) {
                    return false;
                }
                if (popped == '{' && '}' != c) {
                    return false;
                }
                if (popped == '(' && ')' != c) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
