package leetcode.dfs.tree;

import java.util.LinkedList;
import java.util.Queue;

public class FindBottomLeftValue {
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return -1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int left = -1;

        while (!queue.isEmpty()) {
            var polled = queue.poll();
            left = polled.val;
            if (polled.right != null) queue.add(polled.right);
            if (polled.left != null) queue.add(polled.left);
        }

        return left;
    }
}
