package leetcode.linkedlist;

class SwapNodesInPairs {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        // Connecting nodes
        head.next = node2;
        node2.next = node3;
        node3.next = node4;

        swapPairs(head);
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        var current = dummy;
        current.next = head;

        while (current.next != null && current.next.next != null) {
            var first = current.next;
            var second = current.next.next;

            var temp = second.next;
            second.next = first;
            first.next = temp;
            current.next = second;

            current = first;
        }

        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
