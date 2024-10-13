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
