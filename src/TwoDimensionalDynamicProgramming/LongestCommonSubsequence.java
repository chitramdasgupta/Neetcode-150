package TwoDimensionalDynamicProgramming;

// Time - O(m * n)
// Space - O(m * n)
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int rows = text1.length();
        int cols = text2.length();

        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = rows - 1; i >= 0; --i) {
            for (int j = cols - 1; j >= 0; --j) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp[0][0];
    }
}
