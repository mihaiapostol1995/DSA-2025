package leetcode.linkedlist;

class PartitionList {
    public static void main(String[] args) {

    }

    public static ListNode partition(ListNode head, int x) {
        ListNode currentBefore = new ListNode(0);
        ListNode initialBefore = currentBefore;
        ListNode currentAfter = new ListNode(0);
        ListNode initialAfter = currentAfter;

        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                currentBefore.next = cur;
                currentBefore = cur;
            } else {
                currentAfter.next = cur;
                currentAfter = cur;
            }
            cur = cur.next;
        }

        currentAfter.next = null;
        currentBefore.next = initialAfter.next;

        return initialBefore.next;
    }
}
