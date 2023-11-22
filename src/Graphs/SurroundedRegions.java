package Graphs;

// Time - O(mn)
// Space - O(mn)
public class SurroundedRegions {
    public void solve(char[][] board) {
        int numRows = board.length;
        int numCols = board[0].length;

        // Convert un-surrounded regions to 'T'
        for (int i = 0; i < numRows; ++i) {
            dfsCapture(board, i, 0);
            dfsCapture(board, i, numCols - 1);
        }

        for (int i = 0; i < numCols; ++i) {
            dfsCapture(board, 0, i);
            dfsCapture(board, numRows - 1, i);
        }

        // Convert surrounded regions to 'X'
        // Convert un-surrounded regions to 'O'
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }

                if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    void dfsCapture(char[][] board, int r, int c) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != 'O') {
            return;
        }

        board[r][c] = 'T';
        dfsCapture(board, r + 1, c);
        dfsCapture(board, r - 1, c);
        dfsCapture(board, r, c + 1);
        dfsCapture(board, r, c - 1);
    }
}
