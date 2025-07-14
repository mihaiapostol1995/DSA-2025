package leetcode.dfs.tree;

import java.util.LinkedList;
import java.util.Queue;

class SerializeAndDeserializeBST {
}

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
        sb.append(root.val).append(" ");
        serializeBst(root.left, sb);
        serializeBst(root.right, sb);
    }

    // Decodes your encoded data to tree.
    // still preorder traversal! but this achieves the best BALANCE!
    public TreeNode deserialize(String data) {
        Queue<TreeNode> queue = new LinkedList<>();
        for (var s: data.split(" ")) {
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