package leetcode.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class CircularArrayLoop {

    public static void main(String[] args) {
        var c = new CircularArrayLoop();
        int[] nums = {2, 2, 2, 2, 2, 4, 7};
//        c.circularArrayLoop(nums);
//        c.circularArrayLoop(new int[]{1,2,3,4,5});
        c.circularArrayLoop(new int[]{-1,-2,-3,-4,-5,6});
    }

    // another hard
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        boolean[] globalVisited = new boolean[n]; // Avoid re-processing

        outerLoop:
        for (int i = 0; i < n; i++) {
            if (globalVisited[i]) continue;

            boolean[] visited = new boolean[n];
            boolean forward = nums[i] > 0;
            int current = i;

            while (!visited[current]) {
                visited[current] = true;
                globalVisited[current] = true;

                int next = ((current + nums[current]) % n + n) % n;

                // Check for self-loop (invalid)
                if (next == current) break;

                // Check direction consistency
                if ((nums[next] > 0) != forward) {
                    continue outerLoop;
                }

                current = next;
            }

            // If we found a cycle and it's not a self-loop
            if (visited[current]
                    && current != ((current + nums[current]) % n + n) % n) {
                return true;
            }
        }

        return false;
    }
}
