# Introduction to graphs
## Types of graphs
1. Undirected graphs
2. Directed graphs

## Cycle
If we start traversing from a node and end up in the same node then the graph has a cycle.

Examples: Directed Acyclic Graph (DAG): a directed graph with no cycles.

## Path
A path is a collection of connected nodes in a graph that we can traverse.

NOTE: A node cannot appear twice in a path.

## Degree
### Undirected graph
The number of edges attached to a node is its degree.

```
Total degree of an undirected graph = 2 * no. of edges
```

### Directed graph
Indegree: The number of edges going out of a node

Outdegree: The number of edges going into a node.

## Representation
### Adjacency matrix
```cpp
int main() {
  int n, m;
  cin >> n >> m;
  int adj[n + 1][m + 1];

  // If edge weights is given, use the weight instead of 1
  for (int i = 0; i < m; ++i) {
    int u, v;
	cin >> u >> v;
	adj[u][v] = 1;
	adj[v][u] = 1; // Add this for undirected graphs
  }
}
```

Space is O(V ^ 2).

### Adjacency list
```cpp
int main() {
  int n, m;
  cin >> n >> m;
  vector<int> adj[n + 1];

  // If edge weights is given, it will be a vector of pairs
  for (int i = 0; i < m; ++i) {
    int u, v;
	cin >> u >> v;
	adj[u].push_back(v);
	adj[v].push_back(u); // Add this for undirected graphs
  }
}
```

Space is O(E)

## Connected components
A graph can consis of multiple separate connected components, where each connected component is a subgraph which can be traversed starting from any node in that subgraph.

If we start traversal from one node of a component, we will visit all the nodes in that component in one traversal call, but we will not visit any node in the other components in that traversal call (we can however make multiple traversal calls starting from a node in each components).

We maintain a `visited array` to mark which nodes we have visited and which we are yet to.

# Traversals
## Breadth-First Search (BFS) traversal
