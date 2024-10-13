# Rotting Oranges

```java
class Solution {
    private int rows;
    private int cols;
    private int[][] directions = {
        {1, 0},   // Down
        {0, -1},  // Left
        {-1, 0},  // Up
        {0, 1}    // Right
    };

    public int orangesRotting(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        int fresh = 0;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 2) {
                    q.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    ++fresh;
                }
            }
        }

        int time = 0;
        while (!q.isEmpty() && fresh > 0) {
            int len = q.size();
            for (int i = 0; i < len; ++i) {
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];

                for (int[] dir : directions) {
                    int newRow = dir[0] + row;
                    int newCol = dir[1] + col;
                    if (newRow < 0 || newRow >= rows || newCol < 0 || 
                        newCol >= cols || grid[newRow][newCol] != 1) {
                        continue;
                    }

                    --fresh;
                    grid[newRow][newCol] = 2;
                    q.add(new int[]{newRow, newCol});
                }
            }
            ++time;
        }

        return fresh == 0 ? time : -1;
    }
}

```

# Count Connected Components

```java
class Solution {
    private int numNodes;
    private int[] ranks;
    private int[] parents;

    public int countComponents(int n, int[][] edges) {
        numNodes = n;
        ranks = new int[numNodes];
        parents = new int[numNodes];

        for (int i = 0; i < numNodes; ++i) {
            ranks[i] = 1;
            parents[i] = i;
        }

        int res = numNodes;
        for (int[] edge : edges) {
            if (unionFind(edge[0], edge[1])) {
                --res;
            }
        }

        return res;
    }

    private boolean unionFind(int first, int second) {
        int p1 = find(first);
        int p2 = find(second);

        if (p1 == p2) {
            return false;
        }

        if (ranks[p1] < ranks[p2]) {
            ranks[p2] += ranks[p1];
            parents[p1] = p2;
        } else {
            ranks[p1] += ranks[p2];
            parents[p2] = p1;
        }

        return true;
    }

    private int find(int node) {
        int parent = parents[node];

        while (parent != parents[parent]) {
            parents[parent] = parents[parents[parent]];
            parent = parents[parent];
        }

        return parent;
    }
}

```

# Course Schedule I I

```java
class Solution {
    private Map<Integer, List<Integer>> adjList;
    private Set<Integer> currPath;
    private Set<Integer> visited;
    private List<Integer> res;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        adjList = new HashMap<>();
        for (int i = 0; i < numCourses; ++i) {
            adjList.put(i, new ArrayList<>());
        }
        for (int[] prereq : prerequisites) {
            adjList.get(prereq[0]).add(prereq[1]);
        }

        visited = new HashSet<>();
        currPath = new HashSet<>();

        res = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (!dfs(i)) {
                return new int[] {};
            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    private boolean dfs(int course) {
        if (currPath.contains(course)) {
            return false;
        }

        if (visited.contains(course)) {
            return true;
        }

        currPath.add(course);
        for (int neigh : adjList.get(course)) {
            if (!dfs(neigh)) {
                return false;
            }
        }

        currPath.remove(course);
        visited.add(course);
        res.add(course);

        return true;
    }
}
```

# Redundant Connection

```java
class Solution {
    private int numNodes;
    private int[] ranks;
    private int[] parents;

    public int[] findRedundantConnection(int[][] edges) {
        numNodes = edges.length;

        ranks = new int[numNodes + 1];
        parents = new int[numNodes + 1];
        for (int i = 1; i <= numNodes; ++i) {
            ranks[i] = 1;
            parents[i] = i;
        }

        int[] res = new int[2];
        for (int[] edge : edges) {
            if (!unionFind(edge[0], edge[1])) {
                res = new int[] {edge[0], edge[1]};
                break;
            }
        }

        return res;
    }

    private boolean unionFind(int first, int second) {
        int p1 = find(first);
        int p2 = find(second);

        if (p1 == p2) {
            return false;
        }

        if (ranks[p1] < ranks[p2]) {
            ranks[p2] += ranks[p1];
            parents[p1] = p2;
        } else {
            ranks[p1] += ranks[p2];
            parents[p2] = p1;
        }

        return true;
    }

    private int find(int node) {
        int parent = parents[node];

        while (parent != parents[parent]) {
            parents[parent] = parents[parents[parent]];
            parent = parents[parent];
        }

        return parent;
    }
}
```

# Clone Graph

```java
/*
// Definition for a Node.
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
*/

class Solution {
    private Map<Node, Node> oldToNew;

    public Node cloneGraph(Node node) {
        oldToNew = new HashMap<>();

        return node == null ? null : dfs(node);
    }

    private Node dfs(Node node) {
        if (oldToNew.containsKey(node)) {
            return oldToNew.get(node);
        }

        Node newNode = new Node(node.val);
        oldToNew.put(node, newNode);

        for (Node neigh : node.neighbors) {
            newNode.neighbors.add(dfs(neigh));
        }

        return newNode;
    }
}
```

# Course Schedule I

```java
class Solution {
    private Map<Integer, List<Integer>> adjList;
    private Set<Integer> currPath;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        adjList = new HashMap<>();
        for (int i = 0; i < numCourses; ++i) {
            adjList.put(i, new ArrayList<Integer>());
        }
        for (int[] prereq : prerequisites) {
            adjList.get(prereq[0]).add(prereq[1]);
        }

        currPath = new HashSet<>();

        boolean res = true;
        for (int i = 0; i < numCourses; ++i) {
            if (!dfs(i)) {
                res = false;
                break;
            }
        }

        return res;
    }

    private boolean dfs(int course) {
        if (currPath.contains(course)) {
            return false;
        }

        if (adjList.get(course).isEmpty()) {
            return true;
        }

        currPath.add(course);
        for (int neigh : adjList.get(course)) {
            if (!dfs(neigh)) {
                return false;
            }
        }
        currPath.remove(course);
        adjList.get(course).clear();

        return true;
    }
}
```

# Surrounded Regions

```java
class Solution {
    private char[][] board;
    private int rows;
    private int cols;

    public void solve(char[][] board) {
        this.board = board;
        rows = board.length;
        cols = board[0].length;

        for (int i = 0; i < cols; ++i) {
            dfs(0, i);
            dfs(rows - 1, i);
        }

        for (int i = 0; i < rows; ++i) {
            dfs(i, 0);
            dfs(i, cols - 1);
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 't') {
                    board[i][j] = 'O';
                }
            }
        }

        return;
    }

    private void dfs(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols ||
            board[row][col] != 'O') {
            return;
        }

        board[row][col] = 't';

        dfs(row + 1, col);
        dfs(row, col + 1);
        dfs(row - 1, col);
        dfs(row, col - 1);
    }
}
```

# Graph Valid Tree

```java
class Solution {
    private Map<Integer, List<Integer>> adjList;
    private Set<Integer> currPath;
    private Set<Integer> visited;

    public boolean validTree(int n, int[][] edges) {
        adjList = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            adjList.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        currPath = new HashSet<>();
        visited = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            if (!dfs(i, -1)) {
                return false;
            }
        }

        return visited.size() == n;
    }

    private boolean dfs(int course, int prev) {
        if (course == prev) {
            return true;
        }

        if (currPath.contains(course)) {
            return false;
        }

        if (visited.contains(course)) {
            return true;
        }

        currPath.add(course);
        for (int neigh : adjList.get(course)) {
            if (!dfs(neigh, course)) {
                return false;
            }
        }

        currPath.remove(course);
        visited.add(course);

        return true;
    }
}

```

# Pacific Atlantic Water Flow

```java
class Solution {
    private int[][] heights;
    private int rows;
    private int cols;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        rows = heights.length;
        cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        for (int i = 0; i < cols; ++i) {
            dfs(pacific, 0, i, -1);
            dfs(atlantic, rows - 1, i, -1);
        }

        for (int i = 0; i < rows; ++i) {
            dfs(pacific, i, 0, -1);
            dfs(atlantic, i, cols - 1, -1);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(new ArrayList<>(List.of(i, j)));
                }
            }
        }

        return res;
    }

    private void dfs(boolean[][] grid, int row, int col, int prev) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || 
            grid[row][col] || heights[row][col] < prev) {
            return;
        }

        grid[row][col] = true;

        dfs(grid, row + 1, col, heights[row][col]);
        dfs(grid, row, col + 1, heights[row][col]);
        dfs(grid, row - 1, col, heights[row][col]);
        dfs(grid, row, col - 1, heights[row][col]);
    }
}
```

# Max Area Of Island

```java
class Solution {
    private int[][] grid;
    private int rows;
    private int cols;

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;

        int res = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                res = Math.max(dfs(i, j), res);
            }
        }

        return res;
    }

    private int dfs(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols ||
            grid[row][col] != 1) {
            return 0;
        }

        grid[row][col] = 0;

        return 1 + dfs(row + 1, col) + dfs(row, col + 1) + 
            dfs(row - 1, col) + dfs(row, col - 1);
    }
}
```

# Walls And Gates

```java
class Solution {
    private int rows;
    private int cols;
    private int[][] directions = {
        {1, 0},   // Down
        {0, -1},  // Left
        {-1, 0},  // Up
        {0, 1}    // Right
    };

    public void islandsAndTreasure(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 0) {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; ++i) {
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];

                for (int[] dir : directions) {
                    int newRow = dir[0] + row;
                    int newCol = dir[1] + col;

                    if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols ||
                        grid[row][col] + 1 > grid[newRow][newCol] ) {
                        continue;
                    }

                    grid[newRow][newCol] = grid[row][col] + 1;
                    q.add(new int[] {newRow, newCol});
                }
            }
        }

        return;
    }
}

```

# Number Of Islands

```java
class Solution {
    private int rows;
    private int cols;
    private char[][] grid;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;

        int res = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '1') {
                    dfs(i, j);
                    ++res;
                }
            }
        }

        return res;
    }

    private void dfs(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols ||
            grid[row][col] != '1') {
            return;
        }

        grid[row][col] = '0';

        dfs(row + 1, col);
        dfs(row, col + 1);
        dfs(row - 1, col);
        dfs(row, col - 1);
    }
}
```

