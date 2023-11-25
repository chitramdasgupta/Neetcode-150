package LinkedList;

// Time - O(n)
// Space - O(1)
public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        int newSlow = 0;
        do {
            newSlow = nums[newSlow];
            slow = nums[slow];
        } while (newSlow != slow);

        return newSlow;
    }
}
