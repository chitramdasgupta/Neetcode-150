package BitManipulation;

// Time - O(n)
// Space - O(1)
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 1; i <= nums.length; ++i) {
            res ^= i;
        }

        for (int num : nums) {
            res ^= num;
        }

        return res;
    }
}
