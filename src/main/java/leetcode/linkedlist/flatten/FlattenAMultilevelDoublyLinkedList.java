package leetcode.linkedlist.flatten;

import java.util.Stack;

class FlattenAMultilevelDoublyLinkedList {
    public static void main(String[] args) {
        var f = new FlattenAMultilevelDoublyLinkedList();
        Node input = f.createInput();
//        f.flattenWrong(input);
        f.flatten(input);
    }

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(head);

        Node prev = null;

        while (!stack.isEmpty()) {
            var cur = stack.pop();

            // initially prev is null
            if (prev != null) {
                prev.next = cur;
                cur.prev = prev;
            }

            if (cur.next != null) {
                stack.push(cur.next);
                // INSTEAD of having a next upper level, just pop the next one
            }
            if (cur.child != null) {
                stack.push(cur.child);
                cur.child = null;
            }

            prev = cur;
        }
        return head;
    }

    public Node flattenWrong(Node head) {
        if (head == null) {
            return null;
        }

        var dummy = new Node();
        dummy.next = head;

        var cur = head;
        while (cur != null) {
            var temp = cur.next;
            Node next = flattenWithChildren(cur);

            if (next != null) {
                // restore link
                cur.next = next;
                next.prev = cur;
            }

            cur = temp;
        }

        return dummy.next;
    }

    public Node flattenWithChildren(Node head) {
        var child = head.child;
        if (child == null) {
            return head;
        }

        child = flattenWithChildren(child);

        var next = head.next;
        head.next = child;
        child.prev = head;

        var curr = child;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = next;
        next.prev = curr;

        head.child = null;

        return head;
    }


    public Node createInput() {
        // Level 0
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);

        one.next = two; two.prev = one;
        two.next = three; three.prev = two;
        three.next = four; four.prev = three;
        four.next = five; five.prev = four;
        five.next = six; six.prev = five;

        // Level 1 (child of 3)
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node nine = new Node(9);
        Node ten = new Node(10);

        seven.next = eight; eight.prev = seven;
        eight.next = nine; nine.prev = eight;
        nine.next = ten; ten.prev = nine;

        three.child = seven;

        // Level 2 (child of 8)
        Node eleven = new Node(11);
        Node twelve = new Node(12);

        eleven.next = twelve; twelve.prev = eleven;

        eight.child = eleven;

        return one; // Head of the list
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node(int val) {
        this.val = val;
    }

    public Node() {}
}