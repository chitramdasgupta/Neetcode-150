1. Min Cost to Connect All Points (Prim's algorithm)

Goal: connect all points with undirected edges to make a graph with the minimum cost / build MST (Minimum Spanning Tree)

```cpp
class Solution {
public:
  int minCostConnectPoints(vector<vector<int>> &points) {
    // Build the adjacency list { node: vector of neighbor nodes }
    unordered_map<int, vector<vector<int>>> adj;
    for (int i = 0; i < points.size(); ++i) {
      int[x1, y1] = points[i];
      for (int j = i + 1; j < points.size(); ++j) {
        int[x2, y2] = points[j];

        int cost = abs(x2 - x1) + abs(y2 - y1);
        adj[i].push_back({cost, j});
        adj[j].push_back({cost, i});
      }
    }

    // Prim's algorithm
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>>
      minHeap;
    minHeap.push({0, 0}); // {{cost, node}}
    unordered_set<int> visited;
    int res = 0;

    while (visited.size() < points.size()) {
      vector<int> node = minHeap.top();
      int point = node[1];
      int cost = node[0];
      minHeap.pop();

      if (visited.contains(point)) {
        continue;
      }

      res += cost;
      visited.insert(point);
      for (vector<int> neigh : adj[point]) {
        int neighPoint = neigh[1];
        int neighCost = neigh[0];
        if (!visited.contains(neighPoint)) {
          minHeap.push({neighCost, neighPoint});
        }
      }
    }

    return res;
  }
};
```

2. Network Delay Time (Dijkstra's algorithm)

Goal: Given a node, visit all the other nodes (connected via directed edges) with the minimum cost. Also possible to find out if all the nodes are reachable from the source node

NOTE: Dijkstra's algorithm cannot deal with negative edge weights

```cpp
class Solution {
public:
  int networkDelayTime(vector<vector<int>> &times, int n, int k) {
    // adjacency list
    unordered_map<int, vector<vector<int>>> adj;
    for (vector<int> point : times) {
      int src = point[0];
      int dest = point[1];
      int cost = point[2];

      adj[src].push_back({cost, dest});
    }

    // Dijkstra's algorithm
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>>
      minHeap;
    minHeap.push({{0, k}}); // {{cost, point}}
    unordered_set<int> visited;
    int res = 0;
    while (!minHeap.empty()) {
      vector<int> node = minHeap.top();
      int point = node[1];
      int cost = node[0];
      minHeap.pop();

      if (visited.contains(point)) {
        continue;
      }

      visited.insert(point);
      res = cost;
      for (vector<int> neigh : adj[point]) {
        int neighPoint = neigh[1];
        int neighCost = neigh[0];

        if (!visited.contains(neighPoint)) {
          minHeap.push({cost + neighCost, neighPoint});
        }
      }
    }

    return visited.size() == n ? res : -1;
  }
};
```

3. Cheapest Flights Within K Stops (Bellman-Ford algorithm)

Goal: Given a node, find the minimum cost path to the given destination node such that we pass through at most k nodes.

NOTE: Bellman-Ford can deal with negative edge weights.

```cpp
class Solution {
public:
  int findCheapestPrice(int n, vector<vector<int>> &flights, int src, int dst,
                        int k) {
    vector<int> prices(n, INT_MAX);
    prices[src] = 0;

    for (int i = 0; i <= k; ++i) {
      vector<int> tempPrices = prices;

      for (vector<int> flight : flights) {
        int source = flight[0];
        int destination = flight[1];
        int cost = flight[2];

        // This means we have not reached the source yet
        if (prices[source] == INT_MAX) {
          continue;
        }

        if (prices[source] + cost < tempPrices[destination]) {
          tempPrices[destination] = prices[source] + cost;
        }
      }

      prices = tempPrices;
    }

    return prices[dst] == INT_MAX ? -1 : prices[dst];
  }
};
```
