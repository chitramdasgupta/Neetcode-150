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
