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

# Target Sum

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int total = Arrays.stream(nums).sum();
        if (target > total || target < -total) {
            return 0;
        }

        int[][] dp = new int[nums.length + 1][2 * total + 1];
        int offset = total;
        dp[0][offset] = 1;

        for (int i = 1; i <= nums.length; ++i) {
            int num = nums[i - 1];
            for (int j = -total; j <= total; ++j) {
                if (dp[i - 1][offset + j] > 0) {
                    if (offset + j - num >= 0) {
                        dp[i][offset + j - num] += dp[i - 1][offset + j];
                    }
                    if (offset + j + num <= 2 * total) {
                        dp[i][offset + j + num] += dp[i - 1][offset + j];
                    }
                }
            }
        }

        return dp[nums.length][offset + target];
    }
}
```

# Interleaving String

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();

        if (m + n != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 1; i <= m; ++i) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= n; ++i) {
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]) ||
                           (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }
}
```

# Edit Distance

```java
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
```

