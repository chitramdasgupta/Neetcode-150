package Backtracking;

import java.util.ArrayList;
import java.util.List;

// Time - O(n 2^n)
// Space - O(n)
public class Subsets {
    List<List<Integer>> ans;

    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        dfs(0, nums, subset);

        return ans;
    }

    void dfs(int i, int[] nums, List<Integer> subset) {
        if (i >= nums.length) {
            ans.add(new ArrayList<>(subset));
            return;
        }

        subset.add(nums[i]);
        dfs(i + 1, nums, subset);

        subset.remove(subset.size() - 1);
        dfs(i + 1, nums, subset);
    }
}
