package leetcode.linkedlist;

class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9,
                new ListNode(9,
                        new ListNode(9,
                                new ListNode(9,
                                        new ListNode(9,
                                                new ListNode(9,
                                                        new ListNode(9)
                                                )
                                        )
                                )
                        )
                )
        );
        ListNode l2 = new ListNode(9,
                new ListNode(9,
                        new ListNode(9,
                                new ListNode(9)
                        )
                )
        );
        addTwoNumbers(l1, l2);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = null;
        ListNode initial = null;
        int rest = 0;
        while (l1 != null || l2 != null) {
            int sum = computeValue(l1) + computeValue(l2) + rest;
            if (sum < 10) {
                if (dummyHead == null) {
                    dummyHead = new ListNode(sum);
                    initial = dummyHead;
                } else {
                    dummyHead.next = new ListNode(sum);
                    dummyHead = dummyHead.next;
                }
                rest = 0;
            } else {
                rest = 1;
                int remaining = sum - 10;
                if (dummyHead == null) {
                    dummyHead = new ListNode(remaining);
                    initial = dummyHead;
                } else {
                    dummyHead.next = new ListNode(remaining);
                    dummyHead = dummyHead.next;
                }
            }
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        if (rest > 0) {
            dummyHead.next = new ListNode(rest);
        }
        return initial;
    }

    static int computeValue(ListNode l1) {
        return l1 == null ? 0 : l1.val;
    }
}