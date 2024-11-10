class Solution {
    public int[] largestSubarray(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }

        int[] res = new int[k];
        System.arraycopy(nums, 0, res, 0, k);

        for (int i = 1; i < nums.length - k + 1; i++) {
            if (isSubarrayGreater(nums, i, res, 0, k)) {
                System.arraycopy(nums, i, res, 0, k);
            }
        }

        return res;
    }

    private boolean isSubarrayGreater(int[] arr1, int start1, int[] arr2, int start2, int length) {
        for (int i = 0; i < length; i++) {
            if (arr1[start1 + i] != arr2[start2 + i]) {
                return arr1[start1 + i] > arr2[start2 + i];
            }
        }
        return false;
    }
}
