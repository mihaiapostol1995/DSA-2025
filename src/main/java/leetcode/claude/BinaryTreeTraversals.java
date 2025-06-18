package leetcode.claude;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BinaryTreeTraversals {

    // Inorder: Left -> Root -> Right
    public void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // Preorder: Root -> Left -> Right
    public void preorder(TreeNode root) {
        if (root == null) return;

        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // Postorder: Left -> Right -> Root
    public void postorder(TreeNode root) {
        if (root == null) return;

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    // Level Order (Breadth-First): Level by level
    public void levelOrder(TreeNode root) {
        if (root == null) return;

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    public static void main(String[] args) {
        BinaryTreeTraversals bt = new BinaryTreeTraversals();

        // Create tree:    1
        //               /   \
        //              2     3
        //             / \
        //            4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Inorder (L-Root-R): ");
        bt.inorder(root);    // Output: 4 2 5 1 3

        System.out.println("\nPreorder (Root-L-R): ");
        bt.preorder(root);   // Output: 1 2 4 5 3

        System.out.println("\nPostorder (L-R-Root): ");
        bt.postorder(root);  // Output: 4 5 2 3 1

        System.out.println("\nLevel Order (BFS): ");
        bt.levelOrder(root); // Output: 1 2 3 4 5
    }
}