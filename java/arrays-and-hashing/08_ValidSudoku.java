class Solution {
    public boolean isValidSudoku(char[][] board) {
        int size = 9;
        boolean[][] rows = new boolean[size][size + 1];
        boolean[][] cols = new boolean[size][size + 1];
        boolean[][] squares = new boolean[size][size + 1];

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                char cell = board[i][j];

                if (cell == '.') {
                    continue;
                }

                int value = cell - '0';
                int square = 3 * (i / 3) + (j / 3);
                if (rows[i][value] || cols[j][value] || squares[square][value]) {
                    return false;
                }

                rows[i][value] = true;
                cols[j][value] = true;
                squares[square][value] = true;
            }
        }

        return true;
    }
}
