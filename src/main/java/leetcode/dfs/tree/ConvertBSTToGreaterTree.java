package leetcode.dfs.tree;

import java.util.LinkedList;
import java.util.Queue;

class ConvertBSTToGreaterTree {

    public static void main(String[] args) {
        var builder = new ConvertBSTToGreaterTree();
        Integer[] input = {
                4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8
        };

        TreeNode root = builder.buildTree(input);
        builder.add(root, 0);
        printBFSWithNulls(root);
    }

    static void printBFSWithNulls(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                System.out.print("null ");
                continue;
            }

            System.out.print(node.val + " ");
            queue.offer(node.left);  // may be null
            queue.offer(node.right); // may be null
        }
    }
//    public TreeNode convertBST(TreeNode root) {
//
//    }

    private int add(TreeNode root, int toAdd) {
        if (root == null) {
            return toAdd;
        }

        var right = add(root.right, toAdd);
        root.val = right + root.val;
        var left = add(root.left, root.val);
        return left;
    }

    public TreeNode buildTree(Integer[] input) {
        if (input.length == 0 || input[0] == null) return null;

        TreeNode root = new TreeNode(input[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < input.length) {
            TreeNode current = queue.poll();

            if (i < input.length && input[i] != null) {
                current.left = new TreeNode(input[i]);
                queue.offer(current.left);
            }
            i++;

            if (i < input.length && input[i] != null) {
                current.right = new TreeNode(input[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }
}
