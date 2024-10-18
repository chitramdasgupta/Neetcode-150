# Min Cost To Connect All Points

```java
class Solution {
    public int minCostConnectPoints(int[][] points) {
        // Create an adjacency list of
        // Index of point: [{distance, index of another point}]
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        for (int i = 0; i < points.length; ++i) {
            adjList.put(i, new ArrayList<>());
        }

        for (int i = 0; i < points.length; ++i) {
            int x = points[i][0];
            int y = points[i][1];

            for (int j = i + 1; j < points.length; ++j) {
                int otherX = points[j][0];
                int otherY = points[j][1];

                int manDist = Math.abs(x - otherX) + Math.abs(y - otherY);
                adjList.get(i).add(new int[] {manDist, j});
                adjList.get(j).add(new int[] {manDist, i});
            }
        }

        // Prim's algorithm
        PriorityQueue<int[]> minHeap =
            new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Set<Integer> visited = new HashSet<>();
        int res = 0;

        minHeap.add(new int[] {0, 0});
        while (visited.size() < points.length) {
            int len = minHeap.size();
            for (int i = 0; i < len; ++i) {
                int[] point = minHeap.poll();
                int dist = point[0];
                int idx = point[1];

                if (visited.contains(idx)) {
                    continue;
                }

                visited.add(idx);
                res += dist;

                for (int[] neigh: adjList.get(idx)) {
                    int neighDist = neigh[0];
                    int neighIdx = neigh[1];

                    if (visited.contains(neighIdx)) {
                        continue;
                    }

                    minHeap.add(new int[] {neighDist, neighIdx});
                }
            }
        }

        return res;
    }
}
```

# Network Delay Time

```java
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
```

# Cheapest Flights Within K Flights

```java
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        for (int i = 0; i < k + 1; ++i) {
            int[] tempPrices = Arrays.copyOf(prices, prices.length);

            for (int[] flight : flights) {
                int source = flight[0];
                int destination = flight[1];
                int price = flight[2];

                if (prices[source] == Integer.MAX_VALUE) {
                    continue;
                }

                tempPrices[destination] =
                    Math.min(prices[source] + price, tempPrices[destination]);
            }

            prices = tempPrices;
        }

        return prices[dst] != Integer.MAX_VALUE ? prices[dst] : -1;
    }
}
```

