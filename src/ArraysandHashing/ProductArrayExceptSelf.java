package ArraysandHashing;

// Time - O(n)
// Space - O(1) not considering the output array
public class ProductArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];

        int prefix = 1;
        for (int i = 0; i < nums.length; ++i) {
            ans[i] = prefix;
            prefix *= nums[i];
        }

        int suffix = 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            ans[i] *= suffix;
            suffix *= nums[i];
        }

        return ans;
    }
}
