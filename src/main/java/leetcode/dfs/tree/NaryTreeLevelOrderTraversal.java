package leetcode.dfs.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

class NaryTreeLevelOrderTraversal {
    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(root.val));

        while (!queue.isEmpty()) {

            List<Node> level = new ArrayList<>();
            while (!queue.isEmpty()) {
                var node = queue.poll();
                level.add(node);
            }

            var list = level
                    .stream()
                    .filter(Objects::nonNull)
                    .map(node -> node.val)
                    .toList();
            result.add(list);

            for (var node : level) {
                for (var child : node.children) {
                    queue.offer(child);
                }
            }

        }
        return result;
    }
}


// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};