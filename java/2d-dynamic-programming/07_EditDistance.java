class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // dp[i][j] represents the number of operations needed to convert the first i characters
        // of the word1 to the first j characters of the second string.
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; ++i) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                char one = word1.charAt(i - 1);
                char two = word2.charAt(j - 1);

                if (one == two) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // dp[i - 1][j] means deleting a character from word1
                    // dp[i][j - 1] means inserting a character from word2
                    // dp[i - 1][j - 1] means replacing the character
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[m][n];
    }
}
