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
