package Graphs;

// Time - O(mn)
// Space - O(mn)
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                maxArea = Math.max(visit(grid, i, j), maxArea);
            }
        }

        return maxArea;
    }

    int visit(int[][] grid, int row, int col) {
        if (row < 0 || col < 0
                || row >= grid.length || col >= grid[0].length
                || grid[row][col] == 0) {
            return 0;
        }

        grid[row][col] = 0;

        return 1 + visit(grid, row + 1, col) + visit(grid, row, col + 1)
                + visit(grid, row - 1, col) + visit(grid, row, col - 1);
    }
}
