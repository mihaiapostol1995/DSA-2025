package leetcode.linkedlist;

class ReverseNodesInKGroup {

    public static void main(String[] args) {
        var r = new ReverseNodesInKGroup();

//        ListNode head1 = buildList(new int[]{1,2,3,4,5});
//        r.reverseKGroup(head1, 2);

        ListNode head1 = buildList(new int[]{1,2});
        r.reverseKGroup(head1, 2);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        var curr = head;
        int count = 0;
        while (curr != null) {
            curr = curr.next;
            count++;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (count >= k) {
            ListNode prev = null;
            ListNode first = groupPrev.next;
            var cur = first;
            for (int i = 0; i < k; i++) {
                // simple reverse
                var next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            // Now `prev` = new head of this group
            // `first` = old head (now tail)
            groupPrev.next = prev;
            first.next = cur;

            // Move groupPrev to the tail of this group
            groupPrev = first;
            count -= k;
        }
        // cjange
        return dummy.next;
    }

    // Helper: build linked list from array
    private static ListNode buildList(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : vals) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper: print linked list
    private static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print("->");
            curr = curr.next;
        }
        System.out.println();
    }
}
