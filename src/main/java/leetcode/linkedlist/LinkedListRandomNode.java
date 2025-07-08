package leetcode.linkedlist;

import java.util.List;
import java.util.Random;

class LinkedListRandomNode {
}

class Solution {

    List<Integer> values;
    Random random = new Random();

    public Solution(ListNode head) {
        var curr = head;
        while (curr != null) {
            values.add(curr.val);
            curr = curr.next;
        }
    }

    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }
}