package TwoDimensionalDynamicProgramming;

// Time - O(n * m)
// Space - O(n * m)
public class EditDistance {
    public int minDistance(String word1, String word2) {
        // The DP table answers the question: for the substring of w1 starting
        // from the i, how many changes will we need to make to w1 to turn it into w2
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // The last row will have the values n, n-1, n-2 ... 0
        // This means that for an empty word1 we can make the following changes
        // to turn it into word2
        for (int i = 0; i < word2.length() + 1; ++i) {
            dp[word1.length()][i] = word2.length() - i;
        }
        // The last column will similarly have the values m, m-1, m-2 ... 0
        for (int i = 0; i < word1.length(); ++i) {
            dp[i][word2.length()] = word1.length() - i;
        }

        // Filling up the DP table
        for (int i = word1.length() - 1; i >= 0; --i) {
            for (int j = word2.length() - 1; j >= 0; --j) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    // Since they are equal we can move to the next indices in
                    // both word1 and word2. The indices [i + 1][j + 1] will have
                    // a value because we are solving bottom up.
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    // We assume that we add the character at the previous
                    // position to i for w1. Hence, we move to the next index for w2
                    int insertion = dp[i][j + 1];
                    // We can move to the next index in w1 (assuming that we have
                    // taken care of the ith position in w1)
                    int deletion = dp[i + 1][j];
                    // We can simply move to the next index in both w1 and w2
                    int replacement = dp[i + 1][j + 1];
                    // We add 1 because we have to perform 1 operation to address the mismatch
                    dp[i][j] = 1 + Math.min(insertion, Math.min(deletion, replacement));
                }
            }
        }

        return dp[0][0];
    }
}
