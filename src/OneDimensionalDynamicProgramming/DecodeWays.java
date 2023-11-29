package OneDimensionalDynamicProgramming;

// Time - O(n)
// Space - O(n)
public class DecodeWays {
    public int numDecodings(String s) {
        // The DP array will have the number of decoding starting from the index
        // Initially, the index = length of string, will have 1 decoding to
        // represent a string of length 1 (In the Backtracking approach it can
        // also mean that we add 1 to the result once we reach the end of the
        // string signifying we found 1 way to decode the string).
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;

        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                // Since 0 is not even a valid mapping
                dp[i] = 0;
            } else {
                // The number of decoding possible is the sum:
                // (the no. of decoding possible from the next position)
                // + (the no. of decoding possible taking into consideration the
                // current char and the next char)
                dp[i] = dp[i + 1];

                if (i + 1 < s.length() && Integer.parseInt(s.substring(i, i + 2)) <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }

        return dp[0];
    }
}
