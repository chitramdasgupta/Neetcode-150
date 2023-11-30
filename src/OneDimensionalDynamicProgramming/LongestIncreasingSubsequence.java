package OneDimensionalDynamicProgramming;

import java.util.Arrays;

// Time - O(n ^ 2)
// Space - O(n)
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        // the dp array stores the longest increasing subsequence starting at
        // the given index
        int[] dp = new int[nums.length];
        // The base case is 1 because every element is an increasing subsequence
        // length 1.
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
