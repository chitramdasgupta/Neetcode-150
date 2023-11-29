package Graphs;

import java.util.*;

// Time - O(m * n)
// Space - O(m * n)
public class WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        int numRows = rooms.length;
        int numCols = rooms[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[] { i, j });
                }
            }
        }

        // The four directions where the BFS will move
        List<int[]> dirs = new ArrayList<>();
        dirs.add(new int[] { -1, 0 });
        dirs.add(new int[] { 0, -1 });
        dirs.add(new int[] { 1, 0 });
        dirs.add(new int[] { 0, 1 });

        // We start from the gates and then mark the valid neighboring cells with the distance of 1;
        // and then from the next iterations we spread from the cells we marked as 1 distance away,
        // and mark its neighboring cells with 1 + 1 = 2, and so on.
        while (!queue.isEmpty()) {
            int row = queue.peek()[0];
            int col = queue.peek()[1];
            queue.poll();

            for (int i = 0; i < dirs.size(); ++i) {
                int x = row + dirs.get(i)[0];
                int y = col + dirs.get(i)[1];

                if (x < 0 || y < 0 || x >= numRows || y > numCols && rooms[x][y] != Integer.MAX_VALUE) {
                    continue;
                }

                rooms[x][y] = rooms[row][col] + 1;
                queue.add(new int[] { x, y });
            }
        }
    }
}
