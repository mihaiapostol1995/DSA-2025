package leetcode.dfs.tree;

import java.util.LinkedList;
import java.util.Queue;

class LowestCommonAncestorOfBinaryTree {
    public static void main(String[] args) {
//        TreeNode root = new TreeNode(3);
//        TreeNode node5 = new TreeNode(5);
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node6 = new TreeNode(6);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node0 = new TreeNode(0);
//        TreeNode node8 = new TreeNode(8);
//        TreeNode node7 = new TreeNode(7);
//        TreeNode node4 = new TreeNode(4);
//
//        // Building the tree structure
//        root.left = node5;
//        root.right = node1;
//        node5.left = node6;
//        node5.right = node2;
//        node1.left = node0;
//        node1.right = node8;
//        node2.left = node7;
//        node2.right = node4;
        // Creating tree nodes
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);

        // Connecting nodes as per input structure
        root.left = node5;
        root.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

//        TreeNode p = root.left;  // Node with value 5
//        TreeNode q = root.right; // Node with value 1

        TreeNode p = root.left;  // Node with value 5
        TreeNode q = root.left.right.right; // Node with value 4

        lowestCommonAncestor(root, p, q);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode left = null;
        int count = 0;
        int leftLeve = 0;
        TreeNode right = null;
        int rightLeve = 0;

        while (!queue.isEmpty()) {
            count++;
            TreeNode node = queue.poll();
            if (node.left != null && node.left.val == p.val) {
                left = node;
                leftLeve = count;
            }
            if (node.right != null && node.right.val == q.val) {
                right = node;
                rightLeve = count;
            }
            if (left != null && right != null) {
                return rightLeve > leftLeve ? left : right;
            }

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return left != null ? left : right;
    }

    static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        var leftPath = lowestCommonAncestor2(root.left, p, q);
        var rightPath = lowestCommonAncestor2(root.right, p, q);

        if (leftPath != null && rightPath != null) {
            return root;
        }

        return leftPath != null ? leftPath : rightPath;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}
