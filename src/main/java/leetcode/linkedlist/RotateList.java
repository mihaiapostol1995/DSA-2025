package leetcode.linkedlist;

class RotateList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        // Connecting nodes
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        rotateRight(head, 2);
    }

    public static ListNode rotateRight(ListNode head, int k) {
        // Handle edge cases
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Step 2: Optimize k (since rotating by length gives same result)
        k = k % length;
        if (k == 0) {
            return head; // No rotation needed
        }

//        var initialHead = head;
//        for (int i = 0; i < k; i++) {
//            var temp = initialHead;
//            while (temp.next.next != null) {
//                temp = temp.next;
//            }
//
//            ListNode penultimate = temp;
//            ListNode last = temp.next;
//
//            penultimate.next = null;
//            last.next = initialHead;
//            initialHead = last;
//        }

        var newTail = head;
        for (int i = 0; i < length - k; i++) {
            newTail = newTail.next;
        }

        tail.next = head;
        var newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}
