package leetcode.dfs.tree;

class RecoverBinarySearchTree {

    TreeNode prev = null;
    TreeNode first = null;
    TreeNode second = null;

    public static void main(String[] args) {

    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);

        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
                second = root;
            } else {
                second = prev;
            }
        }

        inorder(root.right);
    }

    public void recoverTree(TreeNode root) {
        inorder(root);
        swap();
    }

    public void swap() {
        var temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
