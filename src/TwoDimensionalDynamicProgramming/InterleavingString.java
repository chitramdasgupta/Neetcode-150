package TwoDimensionalDynamicProgramming;

// Time - O(m * n)
// Space - O(m * n)
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        // We add 1 to the dimensions because we also want to check if the last character
        // of either s1 or s2 matches s3.
        // The meaning of the DP table is that from the ith row and the jth column,
        // we can interleave the substrings s1 from i and s2 from j to get the string s3
        // from index i + j.
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        // Base case: An empty string is always an interleaving of two empty strings
        dp[s1.length()][s2.length()] = true;

        // NOTE: i + j will give the correct index at the string s3
        for (int i = s1.length(); i >= 0; --i) {
            for (int j = s2.length(); j >= 0; --j) {
                // We check the row below at the same row because we know that the ith character
                // matches, but we also want to check if the s1 substring from the next character
                // is valid as well.
                if (i < s1.length() && s1.charAt(i) == s3.charAt(i + j) && dp[i + 1][j]) {
                    dp[i][j] = true;
                }
                // We do similarly for the string s2.
                else if (j < s2.length() && s2.charAt(j) == s3.charAt(i + j) && dp[i][j + 1]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[0][0];
    }
}
