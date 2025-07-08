package leetcode.stack;

import java.util.Stack;

class RemoveKDigits {
    public static void main(String[] args) {
        var removeKDigits = new RemoveKDigits();
        removeKDigits.removeKdigits("10200", 1);
    }

    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < num.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(num.charAt(i));
            } else {
               var curr = stack.peek();
               while (curr > num.charAt(i) && k > 0) {
                   stack.pop();
                   k--;
                   if (stack.isEmpty()) {
                       break;
                   } else {
                       curr = stack.peek();
                   }
               }
               stack.push(num.charAt(i));
            }
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (stack.size() > 0) {
            sb.append(stack.pop());
        }
        StringBuilder reverse = sb.reverse();
        while (reverse.length() > 0 && reverse.charAt(0) == '0') {
            reverse.deleteCharAt(0);
        }
        return reverse.toString();
    }
}
