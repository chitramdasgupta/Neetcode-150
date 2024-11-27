/*
We will store the x and the corresponding y in the same spot at the number x (this is possible because the maximum nmber in the array is 1000 which will take at most 10 bits):
*/
class Solution {
    public int[] shuffle(int[] nums, int n) {
        for (int i = 0; i < n; ++i) {
            nums[i] = nums[i] << 10;
            nums[i] = nums[i] | nums[i + n];
        }

        int j = 2 * n - 1;
        for (int i = n - 1; i >= 0; --i) {
            int y = nums[i] & ((int) Math.pow(2, 10) - 1);
            int x = nums[i] >> 10;

            nums[j] = y;
            nums[j - 1] = x;

            j -= 2; 
        }

        return nums;
    }
}
