package leetcode;

import java.util.Stack;

class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        String[] array = {"4", "13", "5", "/", "+"};
        evalRPN(array);
    }

    static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "-": {
                    Integer second = stack.pop();
                    Integer first = stack.pop();
                    stack.push(first - second);
                    break;
                }
                case "/": {
                    Integer second = stack.pop();
                    Integer first = stack.pop();
                    stack.push(first / second);
                    break;
                }
                default:
                    stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}
