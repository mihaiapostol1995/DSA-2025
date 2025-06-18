package leetcode.linkedlist;

class RemoveNthElementFromList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//
//        // Connecting nodes
        head.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;

        removeNthFromEnd(head, 2);
    }

    static public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }

        int length = 0;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        if (n == length) {
            return head.next;
        }

        var current = head;
        for (int i = 0; i < length - n - 1; i++) {
            current = current.next;
        }

        // Remove the nth node from end
        current.next = current.next.next;

        return head;
    }

//    static public ListNode removeNthFromEndFast(ListNode head, int n) {
//
//    }
}
