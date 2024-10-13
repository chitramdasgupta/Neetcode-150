class Solution {
    private char[][] board;
    private int rows;
    private int cols;

    public void solve(char[][] board) {
        this.board = board;
        rows = board.length;
        cols = board[0].length;

        for (int i = 0; i < cols; ++i) {
            dfs(0, i);
            dfs(rows - 1, i);
        }

        for (int i = 0; i < rows; ++i) {
            dfs(i, 0);
            dfs(i, cols - 1);
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 't') {
                    board[i][j] = 'O';
                }
            }
        }

        return;
    }

    private void dfs(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols ||
            board[row][col] != 'O') {
            return;
        }

        board[row][col] = 't';

        dfs(row + 1, col);
        dfs(row, col + 1);
        dfs(row - 1, col);
        dfs(row, col - 1);
    }
}
