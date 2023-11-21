package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Time - O(2^n)
// Space - O(n)
public class CombinationSumII {
    List<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, new ArrayList<Integer>(), 0);
        return res;
    }

    void backtrack(int[] candidates, int target, List<Integer> curr, int pos) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = pos; i < candidates.length; ++i) {
            // This checks the loop from running for duplicates
            // but ensures that the loop runs for the first of the duplicates
            if (i > pos && candidates[i] == candidates[i - 1]) {
                continue;
            }
            curr.add(candidates[i]);
            backtrack(candidates, target - candidates[i], curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
}
