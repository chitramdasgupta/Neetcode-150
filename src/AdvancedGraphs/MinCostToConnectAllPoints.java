package AdvancedGraphs;

import java.util.*;

// Prim's algorithm
// Time - O(n^2 log n)
// Space - O(n ^ 2)
public class MinCostToConnectAllPoints {
    public int minCostConnectPoints(int[][] points) {
        int len = points.length;
        // Creating the adjacency list
        // { index of point -> [[neighbor, distance], [another neighbor, respective distance]]
        Map<Integer, List<int[]>> adjacency = new HashMap<>();
        for (int i = 0; i < len; ++i) {
            adjacency.put(i, new ArrayList<>());
        }
        for (int i = 0; i < len; ++i) {
            int[] point = points[i];
            for (int j = i + 1; j < len; ++j) {
                int[] secondPoint = points[j];

                int dist = Math.abs(secondPoint[0] - point[0]) + Math.abs(secondPoint[1] - point[1]);
                adjacency.get(i).add(new int[]{j, dist});
                adjacency.get(j).add(new int[]{i, dist});
            }
        }

        // Prim's algorithm
        // The cost (distance) of connecting the MST
        int cost = 0;
        // The points we have already visited (connected in the MST)
        Set<Integer> visited = new HashSet<>();
        // The points which we are going to visit
        // This is a min heap which will return the closest point to the current one
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // Start off with the first point and the distance to itself is 0
        minHeap.add(new int[]{0, 0});

        // Loop until we have visited all the points
        while (visited.size() < len) {
            int[] popped = minHeap.poll();
            int curr = popped[0];
            int currCost = popped[1];
            // We have already visited this point
            if (visited.contains(curr)) {
                continue;
            }

            // Add the current point to visited (or the MST)
            cost += currCost;
            visited.add(curr);
            // Now put all the neighbors in the min heap so that in the next
            // iteration we can visit the closest point
            for (int[] neighbors : adjacency.get(curr)) {
                if (!visited.contains(neighbors[0])) {
                    minHeap.add(new int[]{neighbors[0], neighbors[1]});
                }
            }
        }

        return cost;
    }
}
