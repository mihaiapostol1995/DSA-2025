package leetcode.dfs.tree;

import java.util.LinkedList;
import java.util.Queue;

// didn't know, revisit
class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        var s = new SerializeAndDeserializeBinaryTree();
        String serialize = s.serialize(buildTree());
        s.deserialize(serialize);
    }

    static TreeNode buildTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        return root;
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeBst(root, sb);
        return sb.toString();
    }

    // pre order traversal so that root is the first one
    private void serializeBst(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null").append("#");
            return;
        }
        sb.append(root.val).append("#");
        serializeBst(root.left, sb);
        serializeBst(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        for (var s: data.split("#")) {
            if (s.isEmpty()) {
                continue;
            } else if (s.equals("null")) {
                queue.offer(null);
            } else {
                queue.offer(new TreeNode(Integer.parseInt(s)));
            }
        }
        return build(queue);
    }

    TreeNode build(Queue<TreeNode> queue) {
        var polled = queue.poll();
        if (polled == null) {
            return null;
        }

        polled.left = build(queue);
        polled.right = build(queue);

        return polled;
    }
}
