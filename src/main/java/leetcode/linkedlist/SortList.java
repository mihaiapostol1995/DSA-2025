package leetcode.linkedlist;

class SortList {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(1);
        ListNode fourth = new ListNode(3);
        head.next = second;
        second.next = third;
        third.next = fourth;

        var s = new SortList();
        s.sortList(head);
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        var slow = head;
        var fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        var prev = slow.next;
        slow.next = null;

        var firstHalf = sortList(head);
        var secondHalf = sortList(prev);

        return mergeSortedLists(firstHalf, secondHalf);
    }

    ListNode mergeSortedLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        var dummy = new ListNode(0);
        var curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            // to store the last one
            curr = curr.next;
        }

        curr.next = list1 == null ? list2 : list1;
        return dummy.next;
    }
}
