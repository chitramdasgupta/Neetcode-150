package ArraysandHashing;

import java.util.HashSet;
import java.util.Set;

// Time - O(n)
// Space - O(n)
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            seen.add(num);
        }

        int maxCount = 0;
        for (int num : nums) {
            if (seen.contains(num - 1)) {
                continue;
            }
            int count = 1;
            // This operation takes constant time; so, even though we have a
            // loop within a loop, the inner loop executes in constant time.
            // As a result, the time complexity is O(n)
            while (seen.contains(num + 1)) {
                count += 1;
                num += 1;
            }
            maxCount = Math.max(count, maxCount);
            if(maxCount > nums.length / 2) {
                break;
            }
        }

        return maxCount;
    }
}
