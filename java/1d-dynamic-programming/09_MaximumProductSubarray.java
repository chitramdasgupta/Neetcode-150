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
