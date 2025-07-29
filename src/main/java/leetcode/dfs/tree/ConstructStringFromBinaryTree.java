package leetcode.dfs.tree;

import java.util.LinkedList;
import java.util.Queue;

class ConstructStringFromBinaryTree {

    public static void main(String[] args) {
        var c = new ConstructStringFromBinaryTree();
        Integer[] values = {1, 2, 3, null, 4};
        TreeNode root = buildTree(values);

        String s = c.tree2str(root);
        System.out.println(s);
    }

    public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        trer2str(root, sb);
        if (sb.length() > 2) {
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    public void trer2str(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        sb.append("(").append(root.val);
        if (root.left == null && root.right != null) {
            sb.append("()");
        }
        trer2str(root.left, sb);
        trer2str(root.right, sb);

        sb.append(")");
    }

    public static TreeNode buildTree(Integer[] values) {
        if (values.length == 0 || values[0] == null) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < values.length) {
            TreeNode current = queue.poll();

            if (i < values.length && values[i] != null) {
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
