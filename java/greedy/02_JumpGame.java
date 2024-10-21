class Solution {
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        Arrays.fill(dp, false);

        int gap = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
          if (nums[i] >= gap) {
            dp[i] = true;
            gap = 1;
          } else {
          ++gap;
          }
        }

        return dp[0];
    }
}
