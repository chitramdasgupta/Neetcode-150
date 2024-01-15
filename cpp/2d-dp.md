1. Unique Paths

```cpp
class Solution {
public:
  int uniquePaths(int m, int n) {
    vector<vector<int>> dp(m, vector<int>(n, 0));
    for (int i = 0; i < m; ++i) {
      dp[i][n - 1] = 1;
    }
    for (int i = 0; i < n; ++i) {
      dp[m - 1][i] = 1;
    }

    for (int i = m - 2; i >= 0; --i) {
      for (int j = n - 2; j >= 0; --j) {
        dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
      }
    }

    return dp[0][0];
  }
};
```

2. Longest Common Subsequence

```cpp
class Solution {
public:
  int longestCommonSubsequence(string text1, string text2) {
    int m = text1.size();
    int n = text2.size();
    vector<vector<int>> dp(m + 1, vector<int>(n + 1));

    for (int i = m - 1; i >= 0; --i) {
      for (int j = n - 1; j >= 0; --j) {
        if (text1[i] == text2[j]) {
          dp[i][j] = 1 + dp[i + 1][j + 1];
        } else {
          dp[i][j] = max(dp[i + 1][j], dp[i][j + 1]);
        }
      }
    }

    return dp[0][0];
  }
};
```

3. Best Time to Buy and Sell Stock with Cooldown ⭐

```cpp
class Solution {
public:
  int maxProfit(vector<int> &prices) {
    int buy = INT_MIN;
    int sell = 0;
    int cool = 0;
    for (int &price : prices) {
      int prevSell = sell;

      // continue holding, or buy after cooldown
      buy = max(buy, cool - price);
      // continue holding, or sell at current price
      sell = max(sell, buy + price);
      // continue holding, or start a new cooldown after selling
      cool = max(cool, prevSell);
    }

    // the maximum of selling or holding at the last price
    return max(sell, cool);
  }
};
```

4. Coin Change II

```cpp
class Solution {
public:
  int change(int amount, vector<int> &coins) {
    vector<int> dp(amount + 1, 0);
    dp[0] = 1; // we can make 0 in 1 way: not selecting any coins
    for (int &coin : coins) {
      for (int a = 1; a <= amount; ++a) {
        // check if the current coin can contribute to the current amount
        if (coin <= a) {
          // add the no. of ways of making the remaining amount after using the
          // current coin
          dp[a] += dp[a - coin];
        }
      }
    }

    return dp[amount];
  }
};
```
