package leetcode.dfs.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class IsSymmetricTree {

    public static void main(String[] args) {
        var f = new IsSymmetricTree();
        f.isSymmetric(createTree());
    }

    public boolean isSymmetric(TreeNode root) {
       return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val
                && isSymmetric(p.left, q.right)
                && isSymmetric(p.right, q.left);
    }


    public static TreeNode createTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(2);
        root.left.right = null;
        root.right.left = new TreeNode(2);

        return root;
    }
}
