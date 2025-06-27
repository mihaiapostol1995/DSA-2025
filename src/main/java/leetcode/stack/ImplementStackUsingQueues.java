package leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

class ImplementStackUsingQueues {
}

class MyStack {

    Queue<Integer> mainQueue;
    Queue<Integer> auxiliaryQueue;

    public MyStack() {
        mainQueue = new LinkedList<>();
        auxiliaryQueue = new LinkedList<>();
    }

    public void push(int x) {
        if (mainQueue.isEmpty()) {
            mainQueue.offer(x);
        } else {
            auxiliaryQueue.offer(x);
            while (!mainQueue.isEmpty()) {
                auxiliaryQueue.offer(mainQueue.poll());
            }
            var temp = auxiliaryQueue;
            auxiliaryQueue = mainQueue;
            mainQueue = temp;
        }
    }

    public int pop() {
        return mainQueue.poll();
    }

    public int top() {
        return mainQueue.peek();
    }

    public boolean empty() {
        return mainQueue.isEmpty();
    }
}