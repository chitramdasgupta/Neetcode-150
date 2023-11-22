package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Time - O(mn)
// Space - O(mn)
public class PacificAtlanticWaterFlow {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int numRows = heights.length;
        int numCols = heights[0].length;
        boolean[][] pacific = new boolean[numRows][numCols];
        boolean[][] atlantic = new boolean[numRows][numCols];

        for (int i = 0; i < numRows; ++i) {
            dfs(heights, i, 0, pacific, -1);
            dfs(heights, i, numCols - 1, atlantic, -1);
        }

        for (int i = 0; i < numCols; ++i) {
            dfs(heights, 0, i, pacific, -1);
            dfs(heights, numRows - 1, i, atlantic, -1);
        }

        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                if (atlantic[i][j] && pacific[i][j]) {
                    res.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        return res;
    }

    void dfs(int[][] heights, int r, int c, boolean[][] visited, int prevHeight) {
        if (r < 0 || c < 0
                || r >= heights.length || c >= heights[0].length
                || visited[r][c] || heights[r][c] < prevHeight) {
            return;
        }

        visited[r][c] = true;
        dfs(heights, r + 1, c, visited, heights[r][c]);
        dfs(heights, r, c + 1, visited, heights[r][c]);
        dfs(heights, r - 1, c, visited, heights[r][c]);
        dfs(heights, r, c - 1, visited, heights[r][c]);
    }
}
