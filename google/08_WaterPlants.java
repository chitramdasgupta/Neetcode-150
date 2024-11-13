class Solution {
    public int minSteps(int[] nums, int capacity) {
        int res = 0;
        int curr = capacity;

        for (int i = 0; i < nums.length; i++) {
            if (curr < nums[i]) {
                res += (2 * i);
                curr = capacity;
            }

            curr -= nums[i];
            ++res;
        }

        return res;
    }
}
