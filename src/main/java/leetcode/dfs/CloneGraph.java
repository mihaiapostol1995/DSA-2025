package leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CloneGraph {

    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static void main(String[] args) {
        Map<Node, Node> map = new HashMap<>();

        // Creating nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // Connecting nodes as per adjacency list
        node1.neighbors.addAll(Arrays.asList(node2, node4));
        node2.neighbors.addAll(Arrays.asList(node1, node3));
        node3.neighbors.addAll(Arrays.asList(node2, node4));
        node4.neighbors.addAll(Arrays.asList(node1, node3));

        Node node = cloneGraph(node1, map);
    }

    static Node cloneGraph(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }

        Node newNode = new Node(node.val);
        map.put(node, newNode);
        for (Node neighbor : node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor, map));
        }

        return newNode;
    }
}
