package leetcode.dfs.tree;

import java.util.LinkedList;
import java.util.Queue;

class CountCompleteTreeNodes {
    public static void main(String[] args) {
        Integer[] values = {1, 2, 3, 4, 5, 6};
        TreeNode root = buildCompleteTree(values);

        countNodes(root);
    }

    public static int countNodes(TreeNode root) {
        int right = countRight(root);
        int left = countLeft(root);

        if (right == left) {
            return (int) (Math.pow(2, right) - 1);
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static int countLeft(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.left;
        }
        return height;
    }

    private static int countRight(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.right;
        }
        return height;
    }



    public static TreeNode buildCompleteTree(Integer[] values) {
        if (values.length == 0 || values[0] == null) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < values.length) {
            TreeNode current = queue.poll();
            if (values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++;

            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }
}
