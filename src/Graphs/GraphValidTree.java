package Graphs;

import java.util.*;

// Time - O(n)
// Space - O(n)
public class GraphValidTree {
    // A graph is a valid tree if
    // 1. Every node is connected
    // 2. There is no loop in the tree
    Map<Integer, List<Integer>> adj;
    Set<Integer> visited;

    public boolean validTree(int n, int[][] edges) {
        // A graph with no nodes is a valid tree
        if (n == 0) {
            return true;
        }

        // Building the adjacency list
        adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        visited = new HashSet<>();

        // There are no loops and all the nodes are connected
        return dfs(0, -1) && n == visited.size();
    }

    boolean dfs(int curr, int prev) {
        if (visited.contains(curr)) {
            // This means that we are visiting the same node twice and hence
            // there is a loop in the graph
            return false;
        }

        visited.add(curr);
        for (int next : adj.get(curr)) {
            // We ignore the parent of the current node to avoid erroneously
            // flagging the traversal back to the parent node as a loop
            if (next == prev) {
                continue;
            }

            // If we detect a loop return false
            if (!dfs(next, curr)) {
                return false;
            }
        }

        return true;
    }
}
