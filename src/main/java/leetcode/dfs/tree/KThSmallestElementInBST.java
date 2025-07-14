package leetcode.dfs.tree;

import java.util.ArrayList;
import java.util.List;

class KThSmallestElementInBST {

    static int COUNT = 0;

    public static void main(String[] args) {
        Integer[] input = {5, 3, 6, 2, 4, null, null, 1};
        TreeNode root = buildBST(input);

        int result = kthSmallest(root, 3);
        System.out.println(result);
    }


    public static int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }

        var left = kthSmallest(root.left, k);
        COUNT++;
        var middle = 0;
        // in order traversal!
        if (k == COUNT) {
            middle = root.val;
        }
        var right = kthSmallest(root.right, k);

        return Math.max(middle, Math.max(right, left));
    }


    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    public static TreeNode buildBST(Integer[] values) {
        TreeNode root = null;
        for (Integer val : values) {
            if (val != null) {
                root = insert(root, val);
            }
        }
        return root;
    }
}
