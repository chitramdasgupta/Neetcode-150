class Solution {
    private static final int MOD = 1_000_000_007;

    int countArrangements(int[] nums, int remainingBalance) {
        int[][] dp = new int[nums.length + 1][remainingBalance + 1];

        // Base case: if we have processed the entire array
        for (int balance = 0; balance <= remainingBalance; balance++) {
            dp[nums.length][balance] = 1;
        }

        // Fill the DP table bottom-up
        for (int index = nums.length - 1; index >= 0; index--) {
            for (int balance = 0; balance <= remainingBalance; balance++) {
                // Option 1: Single student group
                dp[index][balance] = dp[index + 1][balance];

                // Option 2: Form a group with subsequent students
                int currMin = nums[index];
                int currMax = nums[index];
                for (int nextIndex = index + 1; nextIndex < nums.length; nextIndex++) {
                    currMin = Math.min(currMin, nums[nextIndex]);
                    currMax = Math.max(currMax, nums[nextIndex]);

                    int groupImbalance = currMax - currMin;
                    if (groupImbalance > balance) {
                        break;
                    }

                    dp[index][balance] += dp[nextIndex][balance - groupImbalance];
                    dp[index][balance] %= MOD;
                }
            }
        }

        return dp[0][remainingBalance];
    }
}

// Recursion with memoization
/*
class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][] memo;

    int countArrangements(int[] nums, int index, int remainingBalance) {
        if (memo == null) {
            memo = new int[nums.length][remainingBalance + 1];
            for (int i = 0; i < nums.length; i++) {
                Arrays.fill(memo[i], -1);
            }
        }

        // We have processed the entire array, so we have found a valid way
        if (nums.length == index) {
            return 1;
        }

        if (memo[index][remainingBalance] != -1) {
            return memo[index][remainingBalance];
        }

        int totalArrangements = 0;

        // Option 1: Single student group (zero imbalance)
        totalArrangements += countArrangements(nums, index + 1, remainingBalance);

        // Option 2: Form groups with subsequent students
        int currMin = nums[index];
        int currMax = nums[index];
        for (int i = index + 1; i < nums.length; ++i) {
            currMin = Math.min(nums[i], currMin);
            currMax = Math.max(nums[i], currMax);

            int groupImbalance = currMax - currMin;
            if (groupImbalance > remainingBalance) {
                break;
            }

            totalArrangements += countArrangements(nums, i, remainingBalance - groupImbalance);
            totalArrangements %= MOD;
        }

        memo[index][remainingBalance] = totalArrangements;
        return totalArrangements;
    }
}
*/

// Top down recursion
/*
class Solution {
    private static final int MOD = 1_000_000_007;

    int countArrangements(int[] nums, int index, int remainingBalance) {
        // We have processed the entire array, so we have found a valid way
        if (nums.length == index) {
            return 1;
        }

        int totalArrangements = 0;

        // Option 1: Single student group (zero imbalance)
        totalArrangements += countArrangements(nums, index + 1, remainingBalance);

        // Option 2: Form groups with subsequent students
        int currMin = nums[index];
        int currMax = nums[index];
        for (int i = index + 1; i < nums.length; ++i) {
            currMin = Math.min(nums[i], currMin);
            currMax = Math.max(nums[i], currMax);

            int groupImbalance = currMax - currMin;
            if (groupImbalance > remainingBalance) {
                break;
            }

            totalArrangements += countArrangements(nums, i, remainingBalance - groupImbalance);
            totalArrangements %= MOD;
        }

        return totalArrangements;
    }
}
*/
