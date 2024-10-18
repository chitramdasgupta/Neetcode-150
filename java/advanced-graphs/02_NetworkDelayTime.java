class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Adjacency list
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        for (int i = 0; i < n + 1; ++i) {
            adjList.put(i, new ArrayList<>());
        }
        for (int[] time : times) {
            adjList.get(time[0]).add(new int[] {time[2], time[1]});
        }

        // Dijktra's algorithm
        PriorityQueue<int[]> minHeap =
            new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[0]));
        Set<Integer> visited = new HashSet<>();
        int res = -1;

        minHeap.add(new int[] {0, k});
        while (!minHeap.isEmpty()) {
            int len = minHeap.size();
            for (int i = 0; i < len; ++i) {
                int[] point = minHeap.poll();
                int dist = point[0];
                int idx = point[1];

                if (visited.contains(idx)) {
                    continue;
                }

                visited.add(idx);
                res = dist;

                for (int[] neigh : adjList.get(idx)) {
                    int neighDist = neigh[0];
                    int neighIdx = neigh[1];

                    if (visited.contains(neighIdx)) {
                        continue;
                    }

                    minHeap.add(new int[] {dist + neighDist, neighIdx});
                }
            }
        }

        return visited.size() == n ? res : -1;
    }
}
