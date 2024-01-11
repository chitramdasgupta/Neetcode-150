1. Climbing Stairs

```cpp
class Solution {}
public:
  int climbStairs(int n) {
    int res = 1;
    int prev = 1;
    for (int i = 1; i < n; ++i) {
      int temp = prev + res;
      prev = res;
      res = temp;
    }

    return res;
  }
;
```

2. Min Cost Climbing Stairs

```cpp
class Solution {
public:
  int minCostClimbingStairs(vector<int> &cost) {
    for (int i = cost.size() - 3; i >= 0; --i) {
      cost[i] += min(cost[i + 1], cost[i + 2]);
    }
    return min(cost[0], cost[1]);
  }
};
```

3. House Robber

```cpp
class Solution {
public:
  int rob(vector<int> &nums) {
    // second is the last house we robbed
    // first is the house before the last one we robbed
    int first = 0;
    int second = 0;
    
    // { first, second, n, n + 1, ... }
    for (int &num : nums) {
      int temp = max(num + first, second);
      first = second;
      second = temp;
    }
    
    return second;
  }
};
```

The above code is an optimization of the following tabulation approach:

```cpp
class Solution {
public:
  int rob(vector<int> &nums) {
    if (nums.size() == 1) {
      return nums[0];
    }

    vector<int> dp(nums.size(), 0);
    dp[0] = nums[0];
    dp[1] = max(nums[0], nums[1]);

    for (int i = 2; i < nums.size(); ++i) {
      dp[i] = max(nums[i] + dp[i - 2], dp[i - 1]);
    }

    return dp[nums.size() - 1];
  }
};
```

4. House Robber II

```cpp
class Solution {
public:
  int rob(vector<int> &nums) {
    if (nums.size() == 1) {
      return nums[0];
    }
    
    int range1 = helper(nums, 0, nums.size() - 2);
    int range2 = helper(nums, 1, nums.size() - 1);
    
    return max(range1, range2);
  }
  
  int helper(vector<int> &nums, int start, int end) {
    int first = 0;
    int second = 0;
    
    for (int i = start; i <= end; ++i) {
      int temp = (nums[i] + first, second);
      first = second;
      second = first;
    }

    return second;
  }
};
```

5. Longest Palindromic Substring

```cpp
class Solution {
public:
  string longestPalindrome(string s) {
    int maxStart = 0;
    int maxLen = 1;
    for (int i = 0; i < s.size() - 1; ++i) {
      // Odd length palindromes
      expandFromMid(s, i, i, maxStart, maxLen);
      // Even length palindromes
      expandFromMid(s, i, i + 1, maxStart, maxLen);
    }
    
    return s.substr(maxStart, maxLen);
  }
  
private:
  void expandFromMid(string &str, int start, int end, int &maxStart,
                     int &maxLen) {
    while (start >= 0 && end <= str.size() - 1 && str[start] == str[end]) {
      --start;
      ++end;
    }

    int currLen = end - start - 1;
    if (currLen > maxLen) {
      maxLen = currLen;
      maxStart = start + 1;
    }
  }
};
```

6. Palindromic Substrings

```cpp
class Solution {
public:
  int countSubstrings(string s) {
    int count = 0;
    for (int i = 0; i < s.size(); ++i) {
      expandFromMid(s, i, i, count);
      expandFromMid(s, i, i + 1, count);
    }
    
    return count;
  }
  
private:
  void expandFromMid(string &str, int start, int end, int &count) {
    while (start >= 0 && end <= str.size() - 1 && str[start] == str[end]) {
      --start;
      ++end;

      // We have found one palindrome
      ++count;
    }
  }
};
```

7. Decode Ways ⭐

If the code seems confusing. Take the example: s = "123" and walk through the
code, along with drawing the possible decodings.

```cpp
class Solution {
public:
  int numDecodings(string s) {
    // dp represents the no. of ways to decode s from index 0 till i + 1
    vector<int> dp(s.size() + 1, 0);
    // an empty string can only be decoded in one way (not decoded at all)
    dp[0] = 1;
    // The no. of ways the s from 0 till 1 (i.e the first char) can be decoded
    dp[1] = s[0] == '0' ? 0 : 1;

    for (int i = 2; i <= s.size(); ++i) {
      // The single character at i-1 index can be used
      // Think that the current character can be decoded by itself
      // along with all the decodings we have till now
      if (s[i - 1] != '0') {
        dp[i] = dp[i - 1];
      }

      // The char at i-1 and i-2 indices can be used
      if (s[i - 2] == '1' || (s[i - 2] == '2' && s[i - 1] <= '6')) {
        dp[i] += dp[i - 2];
      }
    }

    return dp[s.size()]; // No. of ways to decode s from index 0 till s.size()
  }
};
```

The optimal approach:

```cpp
class Solution {
public:
  int numDecodings(string s) {
    // an empty string can only be decoded in one way (not decoded at all)
    int twoBack = 1;
    // The no. of ways the first char can be decoded
    int oneBack = s[0] == '0' ? 0 : 1;

    int curr = oneBack;
    for (int i = 2; i <= s.size(); ++i) {
      curr = 0;

      // Taking the present char by itself
      if (s[i - 1] != '0') {
        curr += oneBack;
      }

      // Taking the present char and the prev char together
      if (s[i - 2] == '1' || (s[i - 2] == '2' && s[i - 1] <= '6')) {
        curr += twoBack;
      }
      
      twoBack = oneBack;
      oneBack = curr;
    }

    return curr; // No. of ways to decode s from index 0 till s.size()
  }
};
```

8. Coin Change

```cpp
class Solution {
public:
  int coinChange(vector<int> &coins, int amount) {
    // How many coins we need to add up to the value i (index)
    vector<int> dp(amount + 1, amount + 1);
    dp[0] = 0; // We need no coins to add up to 0

    for (int i = 1; i <= amount; ++i) {
      for (int &coin : coins) {
        if (coin > i) {
          continue;
        }

        dp[i] = min(dp[i], 1 + dp[i - coin]);
      }
    }

    return dp[amount] == amount + 1 ? -1 : dp[amount];
  }
};
```

9. Maximum Product Subarray

```cpp
class Solution {
public:
  int maxProduct(vector<int> &nums) {
    int currMin = 1;
    int currMax = 1;
    int res = nums[0];
    for (int &num : nums) {
      int temp = currMax * num;

      currMax = max(max(currMax * n, currMin * n), n);
      currMin = min(min(temp, currMin * n), n);

      res = max(res, currMax);
    }

    return res;
  }
};
```

10. Word Break

```cpp
class Solution {
public:
  bool wordBreak(string s, vector<string> &wordDict) {
    set<string> dict(wordDict.begin(), wordDict.end());

    int strLen = s.size();
    vector<int> dp(strLen + 1, false);
    dp[0] = true; // Word with no chars trivially present in all dictionaries
    for (int i = 1; i <= strLen; ++i) {
      for (int j = i - 1; j >= 0; --j) {
        if (dp[j]) {
          string word = s.substr(j, i - j);

          if (dict.contains(word)) {
            dp[i] = true;
            break;
          }
        }
      }
    }

    return dp[strLen];
  }
};
```

11. Longest Increasing Subsequence

Time - O(n^2)
Space - O(n)

```cpp
class Solution {
public:
  int lengthOfLIS(vector<int> &nums) {
    vector<int> dp(nums.size(), 1);
    int res = 1;
    for (int i = 1; i < nums.size(); ++i) {
      for (int j = 0; j < i; ++j) {
        if (nums[i] > nums[j]) {
          dp[i] = max(dp[i], dp[j] + 1);
        }
      }
      res = max(res, dp[i]);
    }

    return res;
  }
};
```

Optimal approach using *patience sorting*

Time - O(nlog n)
Space - O(n)

```cpp
// Remember: the key insight in this solution is that the last element of the
tails vector is the last element of the LIS; the tails vector is NOT necessarily
// a valid LIS
class Solution {
public:
  int lengthOfLIS(vector<int> &nums) {
    vector<int> tails(1, nums[0]);
    for (int &num : nums) {
      // If the current number is greater than the last element in the
      // subsquence
      if (num > tails.back()) {
        tails.push_back();
      } else {
        // pos is the first index with a val >= the current number
        int pos = lower_bound(tails.begin(), tails.end(), num) - tails.begin();
        tails[pos] = num;
      }
    }

    return nums.size();
  }
};
```

12. Partition Equal Subset Sum

```cpp
class Solution {
public:
  bool canPartition(vector<int> &nums) {
    int totalSum = accumulate(nums.begin(), nums.end(), 0);
    // An odd value cannot be achieved by adding two numbers
    if (totalSum % 2 == 1) {
      return false;
    }

    totalSum /= 2;
    // Whether index is achievable with the given numbers
    vector<bool> dp(totalSum, false);
    // We can get 0 (if we do not choose any number)
    dp[0] = true;
    for (int &num : nums) {
      for (int sum = totalSum; i >= num; --i) {
        // Whether the sum is achievable with the current num and any of the
        // previously achieved sums
        if (dp[sum - num]) {
          dp[sum] = true;
        }
      }
    }

    return dp[totalSum];
  }
};
```
