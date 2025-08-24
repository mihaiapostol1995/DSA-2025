package leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

class JumpGameVII {

    public static void main(String[] args) {
        var j = new JumpGameVII();
        boolean b = j.canReach("011010", 2, 3);
        System.out.println(b);
    }

    public boolean canReach(String s, int minJump, int maxJump) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        while (!q.isEmpty()) {
            var index = q.poll();
            if (index == s.length() - 1) {
                return true;
            }
            var start = index + minJump;
            while (start <= Math.min(index + maxJump, s.length() - 1)) {
                if (s.charAt(start) == '0') {
                    q.offer(start);
                }
                start++;
            }
        }
        return false;
    }
}
