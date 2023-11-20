package OneDimensionalDynamicProgramming;

// Time - O(n)
// Space - O(1)
public class ClimbingStairs {
    public int climbStairs(int n) {
        int first = 1;
        int second = 1;
        while(n >= 2) {
            int temp = first + second;
            second = first;
            first = temp;
            n--;
        }

        return first;
    }
}
