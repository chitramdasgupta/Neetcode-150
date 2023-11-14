package TwoPointers;

// Time - O(n)
// Space - O(1)
public class TwoSum2InputArraySorted {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (numbers[left] + numbers[right] != target) {
            if (numbers[left] + numbers[right] > target) {
                right -= 1;
            } else {
                left += 1;
            }
        }

        return new int[]{ left + 1, right + 1 };
    }
}
