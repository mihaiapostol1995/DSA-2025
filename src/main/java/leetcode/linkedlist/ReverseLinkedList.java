package leetcode.linkedlist;

class ReverseLinkedList {
    public static void main(String[] args) {
        reverseList(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))));
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        var curr = head;

        while (curr != null) {
            var next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
