package leetcode.dfs.tree;

class UnbalancedTree {
    public static void main(String[] args) {

    }

    // postorder
    private int checkHeight(TreeNode node) {
        if (node == null) return 0;

        int left = checkHeight(node.left);
        int right = checkHeight(node.right);

        if (left == -1 || right == -1) {
            return -1;
        }

        // THIS IS THE MOST IMPORTANT CONDITION
        if (Math.abs(left - right) > 1) return -1;

        return 1 + Math.max(left, right);
    }
}
