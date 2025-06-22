package leetcode.linkedlist;

class RemoveLinkedListElements {
    public static void main(String[] args) {
        int[] values = {1,2,3,6,4,5,6};
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        removeElements(head, 6);
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        var dummy = new ListNode(-1);
        dummy.next = head;

        var prev = dummy;
        var next = head;
        while (next != null) {
            if (next.val == val) {
                prev.next = next.next;
                next = next.next;
                continue;
            }
            prev = next;
            next = next.next;
        }

        return dummy.next;
    }
}
