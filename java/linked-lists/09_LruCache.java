class LRUCache {
    private final Node left;
    private final Node right;

    private final Map<Integer, Node> cache;
    private final int capacity;

    public LRUCache(int capacity) {
        left = new Node(-1, -1);
        right = new Node(-1, -1);
        left.next = right;
        right.prev = left;

        cache = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);
        remove(node);
        insert(node);

        return node.val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }

        cache.put(key, new Node(key, value));
        insert(cache.get(key));

        if (cache.size() > capacity) {
            Node lru = left.next;

            remove(lru);
            cache.remove(lru.key);
        }
    }

    private void insert(Node node) {
        Node prev = right.prev;
        Node next = right;

        prev.next = node;
        next.prev = node;

        node.next = next;
        node.prev = prev;
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private static class Node {
        int key;
        int val;
        Node next;
        Node prev;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
