package leetcode.stack;

import java.util.Stack;

class ImplementQueueUsingStacks {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(5);
    }
}

class MyQueue {

    Stack<Integer> mainStack;
    Stack<Integer> secondStack;

    public MyQueue() {
        mainStack = new Stack<>();
        secondStack = new Stack<>();
    }

    public void push(int x) {
        if (mainStack.isEmpty()) {
            mainStack.push(x);
        } else {
            while (!mainStack.isEmpty()) {
                secondStack.push(mainStack.pop());
            }
            mainStack.push(x);
            while (!secondStack.isEmpty()) {
                mainStack.push(secondStack.pop());
            }
        }
    }

    public int pop() {
       return mainStack.pop();
    }

    public int peek() {
        return mainStack.peek();
    }

    public boolean empty() {
        return mainStack.isEmpty();
    }
}