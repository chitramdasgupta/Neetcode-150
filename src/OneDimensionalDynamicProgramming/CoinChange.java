package OneDimensionalDynamicProgramming;

// Time - O(amount * coin)
// Space = O(amount)
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        // We will solve the problem from 0 all the way to amount
        // dp[0] = o because it takes 0 coins to reach amount 0.
        // We initialize the dp array from 1 to end with the
        // maximum number possible here (amount + 1), so that we
        // can replace the number at that index with the minimum
        // number of coins required to reach that amount
        int[] dp = new int[amount + 1];
        for (int i = 1; i < dp.length; ++i) {
            dp[i] = amount + 1;
        }

        for (int i = 1; i <= amount; ++i) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]); // The 1 is for the current coin
                }
            }
        }

        // Still the maximum meaning that we cannot make up the amount with the
        // given coin denominations
        if (dp[amount] == amount + 1) {
            return -1;
        }

        return dp[amount];
    }
}
