# Unique Paths

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        dp[n] = 0;

        for (int i = m - 2; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                dp[j] += dp[j + 1];
            }
        }

        return dp[0];
    }
}
```

# Longest Common Subsequence

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text2.length() + 1][text1.length() + 1];
        for (int i = text2.length() - 1; i >= 0; --i) {
            for (int j = text1.length() - 1; j >= 0; --j) {
                if (text2.charAt(i) == text1.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp[0][0];
    }
}
```

# Best Time To Buy And Sell Stock With Cooldown

```java
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int hold = 0;        // dp[i+1][0]
        int notHold = 0;     // dp[i+1][1]
        int notHoldNext = 0; // dp[i+2][1]

        for (int i = prices.length - 1; i >= 0; i--) {
            int tempHold = Math.max(hold, prices[i] + notHoldNext);
            int tempNotHold = Math.max(notHold, -prices[i] + hold);

            notHoldNext = notHold;
            hold = tempHold;
            notHold = tempNotHold;
        }

        return notHold;
    }
}
```

# Coin Change I I

```java
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i <= amount; ++i) {
                if (i < coin) {
                    continue;
                }

                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }
}
```

