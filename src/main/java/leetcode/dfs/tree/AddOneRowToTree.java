package leetcode.dfs.tree;

import java.util.LinkedList;
import java.util.Queue;

class AddOneRowToTree {

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0;

        while (!queue.isEmpty()) {
            i++;
            if (i == depth - 1) {
                var size = queue.size();
                for (int j = 0; j < size; j++) {
                    var polled = queue.poll();

                    var newNodeLeft = new TreeNode(val);
                    var left = polled.left;
                    polled.left = newNodeLeft;
                    newNodeLeft.left = left;

                    var newNodeRight = new TreeNode(val);
                    var right = polled.right;
                    polled.right = newNodeRight;
                    newNodeRight.right = right;
                }

                break;
            } else {
                for (int j = 0; j < queue.size(); j++) {
                    var polled = queue.poll();
                    if (polled.left != null) {
                        queue.offer(polled.left);
                    }
                    if (polled.right != null) {
                        queue.offer(polled.right);
                    }
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {

    }
}
