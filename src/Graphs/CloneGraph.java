package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Time - O(v + e)
// Space - O(v)
public class CloneGraph {
    Map<Integer, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (visited.containsKey(node.val)) {
            return visited.get(node.val);
        }

        Node newNode = new Node(node.val, new ArrayList<Node>());
        visited.put(node.val, newNode);

        for (Node neighbor : node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor));
        }
        return newNode;
    }
}

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
