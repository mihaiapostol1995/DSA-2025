package leetcode.dfs.tree;

class ValidateBinarySearchTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Creating nodes
        TreeNode root = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        TreeNode node3 = new TreeNode(3);
        TreeNode node7 = new TreeNode(7);

        // Connecting nodes
        root.left = node4;
        root.right = node6;
        node6.left = node3;
        node6.right = node7;

        isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }

        if (root.val > max || root.val < min) {
            return false;
        }

        // smart!
        return isValidBST(root.right, root.val, max)
                && isValidBST(root.left, min, root.val);
    }
}
