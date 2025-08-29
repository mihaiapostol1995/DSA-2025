package leetcode.dfs.tree;

import java.util.LinkedList;
import java.util.Queue;

class SerializeAndDeserializeBST {

    public static void main(String[] args) {
        var c = new Codec();
//        String serialize = c.serialize(buildTree());
//        TreeNode deserialize = c.deserialize(serialize);
//        preoder(deserialize);

        int[] values = {1, 2, 3, 4, 5};
        TreeNode root = buildBalanced(values, 0, values.length - 1);
        inorder(root);
        System.out.println();

        String serialize = c.serialize(root);
        TreeNode deserialize = c.deserialize(serialize);
        inorder(deserialize);
    }

    // unbalanced
    static TreeNode buildTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        return root;
    }

    static TreeNode buildBalanced(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBalanced(nums, left, mid - 1);
        node.right = buildBalanced(nums, mid + 1, right);
        return node;
    }

    static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }
}

// didn't know, revisit
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeBst(root, sb);
        return sb.toString();
    }

    // pre order traversal so that root is the first one
    private void serializeBst(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append("#");
        serializeBst(root.left, sb);
        serializeBst(root.right, sb);
    }

    // Decodes your encoded data to tree.
    // still preorder traversal! but this achieves the best BALANCE!
    // how to remember: deserialization starts with ROOT
    public TreeNode deserialize(String data) {
        Queue<TreeNode> queue = new LinkedList<>();
        for (var s: data.split("#")) {
            queue.offer(new TreeNode(Integer.parseInt(s)));
        }
        return build(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    TreeNode build(Queue<TreeNode> queue, int min, int max) {
        if (queue.isEmpty()) {
            return null;
        }
        var peek = queue.peek();
        ///  i will forget this...
        if (peek.val < min || peek.val > max ) {
            return null;
        }

        queue.poll();
        peek.left = build(queue, min, peek.val);
        peek.right = build(queue, peek.val, max);

        return peek;
    }
}