1. Number of Islands

```cpp
class Solution {
public:
  int numIslands(vector<vector<char>> &grid) {
    int res = 0;
    for (int i = 0; i < grid.size(); ++i) {
      for (int j = 0; j < grid[0].size(); ++j) {
        if (grid[i][j] == '1') {
          dfs(grid, i, j);
          ++res;
        }
      }
    }

    return res;
  }

private:
  void dfs(vector<vector<char>> &grid, int row, int col) {
    if (row < 0 || row >= grid.size() || col < 0 || col >= grid[0].size() ||
        grid[row][col] != '1') {
      return;
    }

    grid[row][col] = '#';

    dfs(grid, row, col + 1);
    dfs(grid, row + 1, col);
    dfs(grid, row - 1, col);
    dfs(grid, row, col - 1);

    return;
  }
};
```

2. Clone Graph

We do a BFS on the graph, and store the nodes we encounter in a hash map where
the key is the node and the corresponding value is the cloned node.

```cpp
class Solution {
public:
  Node* cloneGraph(Node* node) {
    if (node == nullptr) {
      return nullptr;
    }
    
    Node* copy = new Node(node->val);
    hash_map[node] = copy;
    
    queue<Node*> nodes_queue {{node}};
    
    while(!nodes_queue.empty()) {
      Node* curr = nodes_queue.front();
      nodes_queue.pop();

      for (int i = 0; i < curr->neighbors.size(); ++i) {
        Node* neighbor = curr->neighbors[i];

        if (!hash_map.contains(neighbor)) {
          hash_map[neighbor] = new Node(neighbor->val);
          nodes_queue.push(neighbor);
        }

        hash_map[curr]->neighbors.push_back(hash_map[neighbor]);
      }
    }
    
    return copy;
  }
  
private:
  unordered_map<Node*, Node*> hash_map;
};

/*
// Definition for a Node.
class Node {
public:
  int val;
  vector<Node*> neighbors;

  Node() {
      val = 0;
      neighbors = vector<Node*>();
  }

  Node(int _val) {
      val = _val;
      neighbors = vector<Node*>();
  }

  Node(int _val, vector<Node*> _neighbors) {
      val = _val;
      neighbors = _neighbors;
  }
};
*/
```

3. Max Area of Island

```cpp
class Solution {
public:
  int maxAreaOfIsland(vector<vector<int>> &grid) {
    int res = 0;
    for (int i = 0; i < grid.size(); ++i) {
      for (int j = 0; j < grid[0].size(); ++j) {
        if (grid[i][j] == 1) {
          res = max(res, dfs(grid, i, j));
        }
      }
    }

    return res;
  }

private:
  int dfs(vector<vector<int>> &grid, int row, int col) {
    if (row < 0 || row >= grid.size() || col < 0 || col >= grid[0].size() ||
        grid[row][col] != 1) {
      return 0;
    }

    grid[row][col] = 0;

    return 1 + dfs(grid, row, col + 1) + dfs(grid, row + 1, col) +
           dfs(grid, row, col - 1) + dfs(grid, row - 1, col);
  }
};
```

4. Pacific Atlantic Water Flow

We start from the left, right, top, and bottom elements and go to the interior.
We see if the current cell can be reached from the border elements (meaning that
it can be reached from a cell that is adjacent to the particular ocean). The
result will be the cells which can be reached from both the pacific and atlantic
oceans.

```cpp
class Solution {
public:
  vector<vector<int>> pacificAtlantic(vector<vector<int>> &heights) {
    n_rows = heights.size();
    n_cols = heights[0].size();

    // The grid elements which can be reached from the oceans respectively
    vector<vector<bool>> pacific(n_rows, vector<bool>(n_cols, false));
    vector<vector<bool>> atlantic(n_rows, vector<bool>(n_cols, false));

    // Check which elements can be reached from the leftmost column
    // and the rightmost column
    for (int i = 0; i < n_rows; ++i) {
      dfs(heights, pacific, i, 0);
      dfs(heights, atlantic, i, n_cols - 1);
    }

    // Check which elements can be reached from the top column
    // and the bottom column
    for (int i = 0; i < n_cols; ++i) {
      dfs(heights, pacific, 0, i);
      dfs(heights, atlantic, n_rows - 1, i);
    }

    vector<vector<int>> res;
    for (int i = 0; i < n_rows; ++i) {
      for (int j = 0; j < n_cols; ++j) {
        if (pacific[i][j] && atlantic[i][j]) {
          res.push_back({i, j});
        }
      }
    }

    return res;
  }

private:
  int n_rows;
  int n_cols;

  void dfs(vector<vector<int>> &heights, vector<vector<bool>> &visited, int row,
           int col, int prev_height = INT_MIN) {
    if (row < 0 || row >= n_rows || col < 0 || col >= n_cols ||
        visited[row][col] || heights[row][col] < prev_height) {
      return;
    }

    visited[row][col] = true;

    dfs(heights, visited, row, col + 1, heights[row][col]);
    dfs(heights, visited, row + 1, col, heights[row][col]);
    dfs(heights, visited, row, col - 1, heights[row][col]);
    dfs(heights, visited, row - 1, col, heights[row][col]);
  }
};
```