package leetcode.linkedlist;

class ReorderList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        reorderList(head);
    }

    public static ListNode reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // find middle
        var count = 0;
        var curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        // find middle
        var mid = head;
        for (int i = 0; i < count / 2; i++) {
            mid = mid.next;
        }

        // reverse the list
        var current = mid.next;
        mid.next = null;
        ListNode prev = null;
        while (current != null) {
            var next = current.next;
            current.next = prev;

            prev = current;
            current = next;
        }

        // merge the two lists
        var dummy = new ListNode(0);

        dummy.next = head;
        var newHead = head;
        var currentReversed = prev;
        while (newHead != null && currentReversed != null) {
            var actualNext = newHead.next;
            newHead.next = currentReversed;
            var temp = currentReversed.next;
            currentReversed.next = actualNext;

            newHead = actualNext;
            currentReversed = temp;
        }

        return dummy.next;
    }
}
