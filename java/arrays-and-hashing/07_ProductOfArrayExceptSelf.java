class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;

        int[] suffix = new int[len];
        Arrays.fill(suffix, 1);
        int[] prefix = new int[len];
        Arrays.fill(prefix, 1);

        for (int i = len - 2; i >= 0; --i) {
            suffix[i] = nums[i + 1] * suffix[i + 1];
        }
        for (int i = 1; i < len; ++i) {
            prefix[i] = nums[i - 1] * prefix[i - 1];
        }

        int[] res = new int[len];
        for (int i = 0; i < len; ++i) {
            res[i] = suffix[i] * prefix[i];
        }

        return res;
    }
}
