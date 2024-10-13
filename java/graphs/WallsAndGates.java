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

