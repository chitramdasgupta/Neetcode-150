package AdvancedGraphs;

import java.util.*;

// Dijkstra's algorithm
// Time - O(E log V)
// Space - O(V)
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Creating the adjacency list
        Map<Integer, List<int[]>> adjacency = new HashMap<>();
        // We go till n + 1 because our nodes are labeled 1 through n + 1
        for (int i = 0; i < n + 1; ++i) {
            adjacency.put(i, new ArrayList<>());
        }
        for (int[] time : times) {
            // times is of the format: src, dest, cost (delay)
            adjacency.get(time[0]).add(new int[]{time[1], time[2]});
        }

        // Dijkstra's algorithm
        int cost = 0;
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // As per the problem we start from the kth node
        minHeap.add(new int[]{k, 0});

        // We iterate till we have nodes to process in the heap beacuse we do not know
        // if we can visit (or connect) all the nodes
        while (minHeap.size() > 0) {
            int[] popped = minHeap.poll();
            int curr = popped[0];
            int currCost = popped[1];
            if (visited.contains(curr)) {
                continue;
            }

            // We are not interested in calculating the total cost. Instead, we want to store
            // the cost from the given node to all the other nodes.
            cost = currCost;
            visited.add(curr);
            for (int[] neighbors : adjacency.get(curr)) {
                if (!visited.contains(neighbors[0])) {
                    // Store the cost from to reach the current node + cost to reach the
                    // neighbor or in other words, the cost to reach the neighbor
                    minHeap.offer(new int[]{neighbors[0], currCost + neighbors[1]});
                }
            }
        }

        return visited.size() == n ? cost : -1;
    }
}
