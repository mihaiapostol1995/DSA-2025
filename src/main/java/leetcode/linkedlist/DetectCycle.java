package leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

class DetectCycle {
    public static void main(String[] args) {

    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        visited.add(head);

        var slow = head;
        var fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (visited.contains(fast)) {
                return fast;
            }
            visited.add(slow);
        }

        return head;
    }

    public ListNode detectCycleFast(ListNode head) {
        var slow = head;
        var fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if (slow != fast) {
            return head;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

// * Why this works:
//            *
//            * Let's say:
//            * - Distance from head to cycle start = x
//            * - Distance from cycle start to meeting point = y
//            * - Remaining cycle length = z
//            *
//            * When slow and fast meet:
//            * - Slow traveled: x + y
// * - Fast traveled: x + y + z + y = x + 2y + z
// *
//         * Since fast moves twice as fast: 2(x + y) = x + 2y + z
// * Simplifying: 2x + 2y = x + 2y + z
// * Therefore: x = z
// *
//         * This means the distance from head to cycle start equals
// * the distance from meeting point to cycle start!
//            */

}
