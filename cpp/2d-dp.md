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

5. Target Sum

```cpp
class Solution {
public:
  int findTargetSumWays(vector<int> &nums, int target) {
    return backtrack(nums, target, 0, 0);
  }

private:
  map<pair<int, int>, int> memo;

  int backtrack(vector<int> &nums, int target, int idx, int curr) {
    if (idx == nums.size()) {
      return curr == target ? 1 : 0;
    }
    if (memo.contains({idx, curr})) {
      return memo[{idx, curr}];
    }

    memo[{idx, curr}] = backtrack(nums, target, idx + 1, curr + nums[idx]) +
                        backtrack(nums, target, idx + 1, curr - nums[idx]);

    return memo[{idx, curr}];
  }
};
```

6. Interleaving String

```cpp
class Solution {
public:
  bool isInterleave(string s1, string s2, string s3) {
    if (s1.size() + s2.size() != s3.size()) {
      return false;
    }

    vector<vector<bool>> dp(s1.size() + 1, vector<bool>(s2.size() + 1, false));
    dp[s1.size()][s2.size()] = true;
    for (int i = s1.size(); i >= 0; --i) {
      for (int j = s2.size(); j >= 0; --j) {
        if ((i < s1.size() && s1[i] == s3[i + j] && dp[i + 1][j]) ||
            (j < s2.size() && s2[j] == s3[i + j] && dp[i][j + 1])) {
          dp[i][j] = true;
        }
      }
    }

    return dp[0][0];
  }
};
```

7. Edit Distance

```cpp
class Solution {
public:
  int minDistance(string word1, string word2) {
    vector<vector<int>> dp(word1.size() + 1,
                           vector<int>(word2.size() + 1, INT_MAX));
    for (int i = 0; i <= word1.size(); ++i) {
      dp[i][word2.size()] = word1.size() - i;
    }
    for (int i = 0; i <= word2.size(); ++i) {
      dp[word1.size()][i] = word2.size() - i;
    }

    for (int i = word1.size() - 1; i >= 0; --i) {
      for (int j = word2.size() - 1; j >= 0; --j) {
        if (word1[i] == word2[j]) {
          // No operation needed for equal characters
          dp[i][j] = dp[i + 1][j + 1];
        } else {
          // 1 operation + min. no. of operations for either (insert, delete,
          // replace)
          dp[i][j] = 1 + min(dp[i][j + 1], min(dp[i + 1][j], dp[i + 1][j + 1]));
        }
      }
    }

    return dp[0][0];
  }
};
```
