package Backtracking;

// Time - O(m * n * 4^l) where m = rows, n = cols, l = length of word
// Space - O(l)
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    Boolean dfs(char[][] board, int r, int c, String word, int pos) {
        if (pos >= word.length()) {
            return true;
        }

        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length
                || board[r][c] != word.charAt(pos)) {
            return false;
        }

        board[r][c] = '0';
        Boolean res = dfs(board, r + 1, c, word, pos + 1) || dfs(board, r - 1, c, word, pos + 1)
                || dfs(board, r, c + 1, word, pos + 1) || dfs(board, r, c - 1, word, pos + 1);
        board[r][c] = word.charAt(pos);
        return res;
    }
}
