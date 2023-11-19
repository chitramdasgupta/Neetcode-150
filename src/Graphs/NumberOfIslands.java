package Graphs;

// Time - O(mn)
// Space - O(mn)
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;

        int count = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (grid[i][j] == '1') {
                    visitDfs(grid, i, j);
                    count += 1;
                }
            }
        }

        return count;
    }

    void visitDfs(char[][] grid, int row, int col) {
        if (row < 0 || col < 0
                || row >= grid.length || col >= grid[0].length
                || grid[row][col] == '0') {
            return;
        }

        grid[row][col] = '0';

        visitDfs(grid, row + 1, col);
        visitDfs(grid, row, col + 1);
        visitDfs(grid, row - 1, col);
        visitDfs(grid, row, col - 1);
    }
}
