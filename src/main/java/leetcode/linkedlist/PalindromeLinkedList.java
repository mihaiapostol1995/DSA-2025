package leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

class PalindromeLinkedList {

    public static void main(String[] args) {
        int[] values = {1, 2, 2, 1};

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        isPalindrome(head);
    }

    public boolean isPalindromeSlow(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int j = list.size() - 1;
        for (int i = 0; i < list.size() / 2; i++) {
            if (list.get(i) != list.get(j)) {
                return false;
            }
            j--;
        }
        return true;
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        var slowPointer = head;
        var fastPointer = head;
        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        ListNode prev = null;
        var curr = slowPointer.next;
        while (curr != null) {
            var temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        while (prev != null) {
            if (head.val != prev.val) {
                return false;
            }
            head = head.next;
            prev = prev.next;
        }
        return true;
    }
}
