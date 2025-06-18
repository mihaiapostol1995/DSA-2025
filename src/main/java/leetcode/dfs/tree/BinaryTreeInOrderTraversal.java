package leetcode.dfs.tree;

import java.util.ArrayList;
import java.util.List;

class BinaryTreeInOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        var list = new ArrayList<Integer>();
        inorderTraversal(root, list);
        System.out.println(list);
    }

    public static void inorderTraversal(TreeNode root, List<Integer> inorder) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, inorder);
        inorder.add(root.val);
        inorderTraversal(root.right, inorder);
    }
}
