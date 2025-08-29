package leetcode.dfs.tree;

import java.util.ArrayList;
import java.util.List;

class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        List<Integer> tree1 = new ArrayList<>();
        List<Integer> tree2 = new ArrayList<>();
        preorder(q, tree1);
        preorder(p, tree2);
        return tree1.equals(tree2);
    }

    private void preorder(TreeNode q, List<Integer> tree1) {
        if (q == null) {
            tree1.add(null);
            return;
        }

        tree1.add(q.val);
        preorder(q.left, tree1);
        preorder(q.right, tree1);
    }


    public boolean isSameTreeEasy(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
}
