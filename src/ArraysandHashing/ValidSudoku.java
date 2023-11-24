package ArraysandHashing;

// Time - O(n^2)
// Space - O(n^2)
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int len = board.length;
        boolean[][] rows = new boolean[len][len];
        boolean[][] cols = new boolean[len][len];
        boolean[][] boxes = new boolean[len][len];

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                char c = board[i][j];
                if (board[i][j] == '.') {
                    continue;
                }

                int idx = c - '0' - 1;
                int box = (i / 3) + (j / 3);

                if (rows[i][idx] || cols[j][idx] || boxes[box][idx]) {
                    return false;
                }

                rows[i][idx] = true;
                cols[j][idx] = true;
                boxes[box][idx] = true;
            }
        }
        return true;
    }
}
