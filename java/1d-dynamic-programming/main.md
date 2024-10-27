# Climbing Stairs

```java
class Solution {
    public int climbStairs(int n) {
        int first = 1;
        int second = 1;
        for (int i = 1; i < n; ++i) {
            int temp = first + second;

            first = second;
            second = temp;
        }

        return second;
    }
}
```

# Min Cost Climbing Stairs

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;

        // If we start from the destination, both the costs are 0
        int one = 0;
        int two = 0;

        for (int i = len - 1; i >= 0; --i) {
            // Recurrence relation:
            // cost if we start at current index =
            // cost[i] + min(cost of taking one step, cost of taking two steps)
            int temp = cost[i] + Math.min(one, two);

            two = one;
            one = temp;
        }

        return Math.min(one, two);
     }
}
```

# House Robber

```java
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int first = nums[0];
        int second = nums[1];

        for (int i = 2; i < nums.length; ++i) {
            // The highest profit including the current number
            int temp = Math.max(first + nums[i], second);

            // The highest profit excluding the current number
            first = Math.max(second, first);

            second = temp;
        }

        return Math.max(first, second);
    }
}
```

# House Robber I I

```java
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int firstWithoutLast = maxProfit(nums, 0, nums.length - 2);
        int lastWithoutFirst = maxProfit(nums, 1, nums.length - 1);

        return Math.max(firstWithoutLast, lastWithoutFirst);
    }

    private int maxProfit(int[] nums, int start, int end) {
        int first = nums[start];
        int second = nums[start + 1];
        for (int i = start + 2; i <= end; ++i) {
            int temp = Math.max(first + nums[i], second);

            first = Math.max(second, first);
            second = temp;
        }

        return Math.max(first, second);
    }
}
```

# Longest Palindromic Subtring

```java
class Solution {
    private int maxStart = 0;
    private int maxLen = 1;

    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); ++i) {
            checkPalindrome(s, i, i); // odd length palindrome
            checkPalindrome(s, i, i + 1); // even length palindrome
        }

        return s.substring(maxStart, maxStart + maxLen);
    }

    private void checkPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && right >= left &&
               s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }

        // Account for the fact that we have gone one step too far
        int len = (right - 1) - (left + 1) + 1;

        if (len > maxLen) {
            maxLen = len;
            maxStart = left + 1;
        }
    }
}
```

# Palindromic Substrings

```java
class Solution {
    private int res;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); ++i) {
            checkPalindrome(s, i, i);
            checkPalindrome(s, i, i + 1);
        }

        return res;
    }

    private void checkPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && right >= left &&
              s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;

            ++res;
        }
    }
}
```

# Decode Ways

```java
class Solution {
    public int numDecodings(String s) {
        int two = 1;
        int one = s.charAt(0) != '0' ? 1 : 0;

        for (int i = 1; i < s.length(); ++i) {
            int temp = 0;
            // We can take the current character by itself
            if (s.charAt(i) != '0') {
                temp += one;
            }

            if (i > 0 && s.charAt(i - 1) != '0' &&
                Integer.parseInt(s.substring(i - 1, i + 1)) >= 0 &&
                Integer.parseInt(s.substring(i - 1, i + 1)) <= 26) {
                // We can take the current character and the previous character
                temp += two;
            }

            two = one;
            one = temp;
        }

        return one;
    }
}
```

# Coin Change

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; ++i) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }

                dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
```

# Maximum Product Subarray

```java
class Solution {
    public int maxProduct(int[] nums) {
        int max = 1;
        int min = 1;

        int res = nums[0];
        for (int i = 0; i < nums.length; ++i) {
            int temp = max;

            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);

            res = Math.max(max, res);
        }

        return res;
    }
}
```

# Word Break

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // An empty string is trivially present in the dictionary
        for (int i = 0; i < s.length(); ++i) {
            for (int j = i; j >= 0; --j) {
                String temp = s.substring(j, i + 1);
                // Check if the substring is present in the subset
                // And also check if the previous part of the string is also breakable
                if (wordSet.contains(temp) && dp[j]) {
                    dp[i + 1] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}
```

# Longest Increasing Subsequence

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < nums.length; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(dp[i], res);
        }

        return res;
    }
}
```

# Partition Equal Subset Sum

```java
package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canPartition(new int[] {1, 2, 5}));
    }
}

class Solution {
    public boolean canPartition(int[] nums) {
        int total = Arrays.stream(nums).sum();
        if (total % 2 != 0) {
            return false;
        }

        total /= 2;

        boolean[] dp = new boolean[total + 1];
        dp[0] = true;

        for (int num : nums) {
            if (num > total) {
                continue;
            }

            for (int sum = total; sum >= num; --sum) {
                if (dp[sum - num]) {
                    dp[sum] = true;
                }
            }
        }

        return dp[total];
    }
}
```
