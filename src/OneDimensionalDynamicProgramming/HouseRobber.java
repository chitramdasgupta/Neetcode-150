package OneDimensionalDynamicProgramming;

// Time - O(n)
// Space - O(1)
public class HouseRobber {
    public int rob(int[] nums) {
        // Assume nums is like: [first, second, n, n+1, ...]
        int first = 0;
        int second = 0;

        for (int num : nums) {
            int temp = Math.max(first + num, second);
            first = second;
            second = temp;
        }

        return second;
    }
}
