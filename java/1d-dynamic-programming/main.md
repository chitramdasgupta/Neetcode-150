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

