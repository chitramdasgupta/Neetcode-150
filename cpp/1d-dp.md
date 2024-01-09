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