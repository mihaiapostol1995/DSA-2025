package leetcode.linkedlist;

class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 3, 4, 5, 6, 6, 6, 7, 8};
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }

        ListNode head = dummy.next; // This is the head of your linked list

        deleteDuplicates(head);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode cur = head;

        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                prev.next = cur;
            } else {
                prev.next = cur;

                prev = cur;
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}
