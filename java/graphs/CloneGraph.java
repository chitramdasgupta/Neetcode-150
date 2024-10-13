/*
// Definition for a Node.
class Node {
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
*/

class Solution {
    private Map<Node, Node> oldToNew;

    public Node cloneGraph(Node node) {
        oldToNew = new HashMap<>();

        return node == null ? null : dfs(node);
    }

    private Node dfs(Node node) {
        if (oldToNew.containsKey(node)) {
            return oldToNew.get(node);
        }

        Node newNode = new Node(node.val);
        oldToNew.put(node, newNode);

        for (Node neigh : node.neighbors) {
            newNode.neighbors.add(dfs(neigh));
        }

        return newNode;
    }
}
