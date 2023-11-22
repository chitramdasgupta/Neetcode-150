package Graphs;

import java.util.LinkedList;
import java.util.Queue;

// Space - O(mn)
// Time - O(mn)
public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int nRows = grid.length;
        int nCols = grid[0].length;

        int nFresh = 0;
        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j < nCols; ++j) {
                if (grid[i][j] == 1) {
                    nFresh += 1;
                } else if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int time = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (nFresh > 0 && !queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; ++i) {
                int[] temp = queue.poll();
                int r = temp[0];
                int c = temp[1];
                for (int[] dir : dirs) {
                    int x = r + dir[0];
                    int y = c + dir[1];

                    if (x >= 0 && y >= 0 && x < nRows && y < nCols && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.offer(new int[]{x, y});
                        nFresh -= 1;
                    }
                }
            }
            time += 1;
        }

        return nFresh == 0 ? time : -1;
    }
}
