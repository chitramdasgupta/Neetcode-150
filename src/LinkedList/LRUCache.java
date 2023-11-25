package LinkedList;

import java.util.HashMap;
import java.util.Map;

// Time - O(1)
// Space - 0(capacity)
public class LRUCache {

    private final Map<Integer, Node> cache;
    private final int capacity;
    private final Node left;
    private final Node right;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();

        // The Least Recently Used
        this.left = new Node(-1, -1);
        // The Most Recently Used
        this.right = new Node(-1, -1);

        this.left.next = this.right;
        this.right.prev = this.left;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // Retrieving the key takes O(1) time
        // Both removal and insertion take O(1) as well
        remove(cache.get(key));
        insert(cache.get(key));
        return cache.get(key).val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }

        cache.put(key, new Node(key, value));
        insert(cache.get(key));

        if (cache.size() > capacity) {
            Node lru = this.left.next;
            remove(lru);
            cache.remove(lru.key);
        }
    }

    // Removing node takes O(1) time
    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    // Inserting node at the right takes O(1) time
    private void insert(Node node) {
        Node prev = this.right.prev;
        Node next = this.right;

        prev.next = node;
        next.prev = node;

        node.next = next;
        node.prev = prev;
    }

    private class Node {
        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
