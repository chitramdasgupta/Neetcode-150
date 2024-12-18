class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; ++i) {
            if (seen.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = seen.get(target - nums[i]);
                break;
            } else {
                seen.put(nums[i], i);
            }
        }

        return res;
    }
}
