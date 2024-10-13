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

