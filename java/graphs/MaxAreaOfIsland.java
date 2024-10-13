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
