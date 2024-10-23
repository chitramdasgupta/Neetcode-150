class Solution {
    private int rows;
    private int cols;

    public boolean exist(char[][] board, String word) {
        this.rows = board.length;
        this.cols = board[0].length;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int row, int col, int index) {
        if (row < 0 || row >= rows || col < 0 || col >= cols ||
            index == word.length() || board[row][col] != word.charAt(index)) {
            return false;
        }

        if (index == word.length() - 1) {
            return true;
        }

        board[row][col] = '#';

        if (dfs(board, word, row, col + 1, index + 1) ||
            dfs(board, word, row + 1, col, index + 1) ||
            dfs(board, word, row, col - 1, index + 1) ||
            dfs(board, word, row - 1, col, index + 1)) {
            return true;
        }

        board[row][col] = word.charAt(index);

        return false;
    }
}
