package leetcode.dfs.tree;

import java.util.ArrayList;
import java.util.List;

class BinaryTreePaths {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        BinaryTreePaths binaryTreePaths = new BinaryTreePaths();
        binaryTreePaths.binaryTreePaths(root, new ArrayList<>());
        System.out.println(binaryTreePaths.paths);
    }

    List<String> paths = new ArrayList<>();

    public void binaryTreePaths(TreeNode root, List<String> nodes) {
        if (root == null) {
            return;
        }

        nodes.add(String.valueOf(root.val));
        if (root.left == null && root.right == null) {
            paths.add(concatenate(nodes));
            nodes.remove(nodes.size() - 1);
            return;
        }
        binaryTreePaths(root.left, nodes);
        binaryTreePaths(root.right, nodes);
        nodes.remove(nodes.size() - 1);
    }

    private String concatenate(List<String> ints) {
        return String.join("->", ints);
    }
}
