package leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

class CopyListWithRandomPointer {
    public static void main(String[] args) {

    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        var current = head;
        while (current != null) {
            Node value = new Node(current.val);
            map.put(current, value);
            current = current.next;
        }

        var cur = head;
        while (cur != null) {
            var second = map.get(cur);
            second.next = map.get(cur.next);
            second.random = map.get(cur.random);

            cur = cur.next;
        }

        return map.get(head);
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}