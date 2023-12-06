package TwoDimensionalDynamicProgramming;

// Time - O(n * m)
// Space - O(n * m)
public class CoinChangeII {
    public int change(int amount, int[] coins) {
        // The DP table is as follows:
        // The cols are 0...amount and the rows are the coins indices
        //    0 1 2 3 4 5
        // 0  1 0 0 0 0 0
        // 1  1
        // 2  1         X
        int[][] dp = new int[coins.length][amount + 1];
        // Our base case: there is 1 way to make amount 0 using any coins--not choosing any
        for (int i = 0; i < coins.length; ++i) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < coins.length; ++i) {
            for (int j = 1; j <= amount; ++j) {
                // We assign the no. of combo if we do not choose the current coin
                if (i > 0) {
                    dp[i][j] = dp[i - 1][j];
                }
                // If there is still some money left after we choose the current coin,
                // we add the number of combinations choosing the current coin will make
                // to the previous value
                if (j - coins[i] >= 0) {
                    // Add the number of combinations choosing the current coin will make
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }

        return dp[coins.length - 1][amount];
    }
}
