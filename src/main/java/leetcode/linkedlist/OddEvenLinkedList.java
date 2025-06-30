package leetcode.linkedlist;

class OddEvenLinkedList {
    public static void main(String[] args) {
        int[] values = {2, 1, 3, 5, 6, 4, 7};

        // Create the head of the list
        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        // Add the rest of the nodes
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        OddEvenLinkedList oddEvenList = new OddEvenLinkedList();
        oddEvenList.oddEvenList(head);
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        var dummy = new ListNode(0);
        dummy.next = head;
        var first = head.next;

        var cur = head;
        while (cur.next != null && cur.next.next != null) {
            var temp = cur.next;
            cur.next = cur.next.next;
            cur = temp;
        }
        cur.next = null;

        cur = dummy.next;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = first;

        return dummy.next;
    }
}
