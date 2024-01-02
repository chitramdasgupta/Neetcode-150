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

5. Surrounded Regions

We can reverse the problem by thinking of capturing everything except the
unsurrounded regions (instead of thinking about capturing the surrounded
regions).

We first mark all the unsurrounded 'o' cells as `t`. Then in the next iteration,
we mark all 'o' as 'x' (as the remaining 'o's are surrounded). If we return the
board, we return with everything captured expect the surrounded cells.

```cpp
class Solution {
public:
  void solve(vector<vector<char>> &board) {
    n_rows = board.size();
    n_cols = board[0].size();

    // Check which of the border cells are 'O's and then run dfs from those
    // to mark all the unsurrounded cells.
    // Top and bottom border row
    for (int i = 0; i < n_cols; ++i) {
      dfs(board, 0, i);
      dfs(board, n_rows - 1, i);
    }
    // Leftmost and rightmost columns
    for (int i = 0; i < n_rows; ++i) {
      dfs(board, i, 0);
      dfs(board, i, n_cols - 1);
    }

    // Now that the unsurrounded 'O' cells are marked. We can mark the
    // remaining 'O' cells as 'X' (as the remaining 'O' cells must be
    // surrounded)
    for (int i = 0; i < n_rows; ++i) {
      for (int j = 0; j < n_cols; ++j) {
        if (board[i][j] == 'O') {
          board[i][j] = 'X';
        } else if (board[i][j] == 't') {
          board[i][j] = 'O';
        }
      }
    }
  }

private:
  int n_rows;
  int n_cols;

  void dfs(vector<vector<char>> &board, int row, int col) {
    if (row < 0 || row >= n_rows || col < 0 || col >= n_cols ||
        board[row][col] != 'O') {
      return;
    }

    board[row][col] = 't';

    dfs(board, row, col + 1);
    dfs(board, row + 1, col);
    dfs(board, row, col - 1);
    dfs(board, row - 1, col);
  }
};
```

6. Rotting Oranges

```cpp
class Solution {
public:
  int orangesRotting(vector<vector<int>> &grid) {
    queue<pair<int, int>> rotten_queue = {};
    int fresh = 0;

    for (int i = 0; i < grid.size(); ++i) {
      for (int j = 0; j < grid[0].size(); ++j) {
        if (grid[i][j] == 2) {
          rotten_queue.push({i, j});
        } else if (grid[i][j] == 1) {
          ++fresh;
        }
      }
    }

    int time = 0;
    // We rot the oranges in a BFS way and increment time after one BFS level
    // from each rotten orange
    while (fresh > 0 && !rotten_queue.empty()) {
      int len = rotten_queue.size();
      // For each rotten orange, we try to contaminate the neighboring orange
      // if it possible. Then we push the neighboring rotten oranges in the
      // queue
      for (int i = 0; i < len; ++i) {
        int row = rotten_queue.front().first;
        int col = rotten_queue.front().second;
        rotten_queue.pop();

        for (pair<int, int> dir : directions) {
          int neigh_row = row + dir.first;
          int neigh_col = col + dir.second;

          if (neigh_row < 0 || neigh_row >= grid.size() || neigh_col < 0 ||
              neigh_col >= grid[0].size() || grid[neigh_row][neigh_col] != 1) {
            continue;
          }

          grid[neigh_row][neigh_col] = 2;
          rotten_queue.push({neigh_row, neigh_col});
          fresh -= 1;
        }
      }
      ++time;
    }

    return fresh == 0 ? time : -1;
  }

private:
  vector<pair<int, int>> directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
};
```

7. Walls and Gates

```cpp
class Solution {
public:
  void wallsAndGates(vector<vector<int>> &rooms) {
    int n_rows = rooms.size();
    int n_cols = rooms[0].size();

    vector<pair<int, int>> gates_queue = {};
    for (int i = 0; i < n_rows; ++i) {
      for (int j = 0; j < n_cols; ++j) {
        if (rooms[i][j] == 0) {
          gates_queue.push({i, j});
        }
      }
    }
    
    while (!gates_queue.empty()) {
      int len = gates_queue.size();
      for (int i = 0; i < len; ++i) {
        int row = gates_queue.front().first;
        int col = gates_queue.front().second;
        gates_queue.pop();

        for (pair<int, int> dir : directions) {
          int neigh_row = row + dir.first;
          int neigh_col = col + dir.second;
          
          if (neigh_row < 0 || neigh_row >= n_rows || neigh_col < 0 ||
              neigh_col >= n_cols || rooms[row][col] == -1 ||
              rooms[row][col] + 1 >= rooms[neigh_row][neigh_col]) {
            continue;
          }

          rooms[neigh_row][neigh_col] = rooms[row][col] + 1;
          gates_queue.push({neigh_row, neigh_col});
        }
      }
    }
  }

private:
  vector<pair<int, int>> directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
};
```

8. Course Schedule

We model the problem with a graph: each course becomes a node, and its
prerequisites list makes for the adjacency list. We then perform a dfs from
each node, and check if there is a cycle; if there is a cycle (then the course
starting from which the cycle arises cannot be completed).

```cpp
class Solution {
public:
  bool canFinish(int numCourses, vector<vector<int>> &prerequisites) {
    // prereq is the adjacency list of the graph, where each element is the
    // list of neighbors of a particular node
    unordered_map<int, vector<int>> prereq{};
    for (vector<int> pre : prerequisites) {
      prereq[pre[0]].push_back(pre[1]);
    }

    unordered_set<int> curr_path{};
    // For each node we check if the path from the node is acyclic (no cycle)
    // If not acyclic (meaning there is a deadlock or loop), then it is not
    // possible to complete the current course
    for (int i = 0; i < numCourses; ++i) {
      if (!acyclic(i, prereq, curr_path)) {
        return false;
      }
    }

    return true;
  }

private:
  // This method does a dfs on the graph from the given node (course)
  bool acyclic(int course, unordered_map<int, vector<int>> &prereq,
               unordered_set<int> curr_path) {
    // we check if we have visited the current node in the current dfs path.
    // If yes, then there is a cycle (hence not acyclic).
    if (curr_path.contains(course)) {
      return false;
    }

    // The current course has no prerequisite, hence can be completed
    if (prereq[course].empty()) {
      return true;
    }

    // visit the current node
    curr_path.insert(course);

    // Check if the prerequisites of the current course can be completed
    for (int next_course : prereq[course]) {
      if (!acyclic(next_course, prereq, curr_path)) {
        return false;
      }
    }

    // If we have come this far, it means that the prerequisites of the course
    // can be completed, and hence the current course can be as well.
    prereq[course].clear();
    curr_path.erase(course);
    return true;
  }
};
```