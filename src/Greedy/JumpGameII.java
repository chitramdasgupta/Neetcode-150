package Greedy;

// Time - O(n)
// Space - O(1)
public class JumpGameII {
    public int jump(int[] nums) {
        // The minimum position we can reach with the previous step
        int left = 0;
        // The maximum position we can reach with the previous step
        int right = 0;
        // Number of steps we have taken
        int numSteps = 0;

        while (right < nums.length - 1) {
            // The farthest position we can reach from this window (left, right)
            int maxJump = 0;
            for (int i = left; i <= right; ++i) {
                maxJump = Math.max(maxJump, i + nums[i]);
            }

            // The next window starts at the next position from the current right
            left = right + 1;
            // The next window ends at the farthest we can go from the current window
            right = maxJump;
            numSteps += 1;
        }

        return numSteps;
    }
}
