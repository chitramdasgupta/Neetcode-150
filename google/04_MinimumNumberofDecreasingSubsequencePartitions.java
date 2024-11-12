// The minimum number of strictly decreasing subsequences = length of the longest inreasing subqeuence (our problem)
// The minimum number of strictly increasing subsequences = length of the longest decreasing subqeuence

class Solution {
    public int minDecreasingPartitions(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int res = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; --i) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            res = Math.max(dp[i], res);
        }

        return res;
    }
}
