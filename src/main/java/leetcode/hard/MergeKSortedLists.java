package leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

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

public class MergeKSortedLists {

    public static void main(String[] args) {
        int[][] input1 = {{1,4,5},{1,3,4},{2,6}};

        // Convert each into ListNode[]
        ListNode[] lists1 = buildListNodeArray(input1);
        var m = new MergeKSortedLists();
        m.mergeKLists(lists1);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparing(node -> node.val));

        for (ListNode head : lists) {
            ListNode curr = head;
            while (curr != null) {
                pq.offer(curr);
                curr = curr.next;
            }
        }

        var dummy = new ListNode();
        var curr = dummy;

        while (!pq.isEmpty()) {
            var node = pq.poll();

            curr.next = node;
            curr = node;
        }
        curr.next = null;

        return dummy.next;
    }


    static ListNode[] buildListNodeArray(int[][] arrays) {
        ListNode[] result = new ListNode[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            result[i] = buildList(arrays[i]);
        }
        return result;
    }

    static ListNode buildList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int num : arr) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }
        return dummy.next;
    }
}
