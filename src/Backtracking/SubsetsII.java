package Backtracking;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// Time - O(n 2^n)
// Space - O(n)
public class SubsetsII {
    List<List<Integer>> res;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, new ArrayList<Integer>(), 0);
        return res;
    }

    void backtrack(int[] nums, List<Integer> curr, int pos) {
        if (pos == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        curr.add(nums[pos]);
        backtrack(nums, curr, pos + 1);

        int num = curr.remove(curr.size() - 1);
        while (pos < nums.length - 1 && nums[pos + 1] == num) {
            pos += 1;
        }
        backtrack(nums, curr, pos + 1);
    }
}
