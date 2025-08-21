package leetcode.stack;

import java.util.Stack;

class LongestValidParentheses {
    public static void main(String[] args) {
        var l = new LongestValidParentheses();
        // )()())
        l.longestValidParentheses(")()())");
    }

    public int longestValidParentheses(String s) {
        int currentCount = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
//                stack.pop();
//                if (!stack.isEmpty()) {
//                    if (stack.peek() == -1) {
//                        currentCount = 2;
//                    } else {
//                        currentCount = Math.max(currentCount, i - stack.peek());
//                    }
//                } else {
//                    stack.push(i);
//                }

                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    currentCount =  Math.max(currentCount, stack.peek() - i);
                }
            }
        }
        return currentCount;
    }
}
