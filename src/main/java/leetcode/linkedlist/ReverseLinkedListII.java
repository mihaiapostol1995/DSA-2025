package leetcode.linkedlist;

class ReverseLinkedListII {

    public static void main(String[] args) {
        int[] values = {1, 2 ,3 ,4};
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }

        ListNode head = dummy.next; // This is the head of your list

        reverseBetween(head, 1, 2);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }

        var dummy = new ListNode(-1);
        dummy.next = head;

        var beforeRange = dummy;
        for (int i = 1; i < left; i++) {
            beforeRange = beforeRange.next;
        }
        // PREV is the most important actor!
        ListNode prev = null;
        var curr = beforeRange.next;
        for (int i = left; i <= right; i++) {
            var next = curr.next;
            curr.next = prev; // breaks the head
            prev = curr;
            curr = next;
        }

        beforeRange.next.next = curr; // last
        beforeRange.next = prev; // first

        return dummy.next;
    }

    public static ListNode reverseList(ListNode head, int right) {
        ListNode prev = null;
        var curr = head;

        while (curr != null && curr.val <= right) {
            var next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }


    public static ListNode reverseBetweenClaude(ListNode head, int left, int right) {
        // Create dummy node to handle edge case where left = 1
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 1: Find the node before the reversal starts
        ListNode prevNode = dummy;
        for (int i = 1; i < left; i++) {
            prevNode = prevNode.next;
        }

        // Step 2: Reverse the section from left to right
        ListNode prev = null;
        ListNode current = prevNode.next;

        for (int i = 0; i <= right - left; i++) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // Step 3: Reconnect the pieces
        // prevNode.next was the first node of original section (now tail of reversed)
        prevNode.next.next = current;  // Connect tail to remaining list
        prevNode.next = prev;          // Connect before part to new head of reversed

        return dummy.next;
    }
}
