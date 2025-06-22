package leetcode;

import java.util.HashMap;

class LRUCache {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }

    HashMap<Integer, Node> cache;
    private int capacity;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Create dummy head and tail nodes
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node oldNode = cache.get(key);
            oldNode.value = value;
            moveToHead(oldNode);
        } else {
            if (cache.size() >= capacity) {
                var remove = removeTail();
                cache.remove(remove.key);
            }
            Node node = new Node(key, value);
            cache.put(key, node);
            addToHead(node);
        }
    }

    private Node removeTail() {
        var remove = tail.prev;
        var prev = remove.prev;

        prev.next = tail;
        tail.prev = prev;
        return remove;
    }

    private void moveToHead(Node node) {
        // remove old reference
        removeNode(node);

        // move to head
        addToHead(node);
    }

    private void addToHead(Node node) {
        var next = head.next;

        head.next = node;
        node.prev = head;

        node.next = next;
        next.prev = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}