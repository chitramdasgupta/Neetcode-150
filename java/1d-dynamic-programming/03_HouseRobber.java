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
