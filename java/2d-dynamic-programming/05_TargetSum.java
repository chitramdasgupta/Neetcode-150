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
