package leetcode.interviews;

import leetcode.dfs.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class RandomTree {
    TreeNode root;
    Random rand = new Random();

    // Helper: update size
    private void updateSize(TreeNode node) {
        if (node == null) return;
        node.size = 1 + (node.left != null ? node.left.size : 0) + (node.right != null ? node.right.size : 0);
    }

    // Insert (BST example)
    public TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insert(node.left, val);
        else node.right = insert(node.right, val);
        updateSize(node);
        return node;
    }

    // Pick random node
    public TreeNode getRandomNode(TreeNode node) {
        if (node == null) return null;

        int index = rand.nextInt(node.size); // 0 to size-1

        int leftSize = (node.left != null) ? node.left.size : 0;
        if (index < leftSize) return getRandomNode(node.left);
        else if (index == leftSize) return node;
        else return getRandomNode(node.right);
    }

//    // Get random leaf (inefficient version)
//    public TreeNode getRandomLeaf(TreeNode node) {
//        List<TreeNode> leaves = new ArrayList<>();
//        collectLeaves(node, leaves);
//        return leaves.get(rand.nextInt(leaves.size()));
//    }
//
//    private void collectLeaves(TreeNode node, List<TreeNode> leaves) {
//        if (node == null) return;
//        if (node.left == null && node.right == null) {
//            leaves.add(node);
//        } else {
//            collectLeaves(node.left, leaves);
//            collectLeaves(node.right, leaves);
//        }
//    }

}

