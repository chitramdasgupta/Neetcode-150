package Backtracking;

import java.util.ArrayList;
import java.util.List;

// Time - O(n 2^n)
// Space - O(n)
public class CombinationSum {
    List<List<Integer>> ans;
    int[] candidates;
    int target;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.ans = new ArrayList<>();
        this.candidates = candidates;
        this.target = target;

        dfs(0, 0, new ArrayList<>());
        return ans;
    }

    void dfs(int i, int curr, List<Integer> combo) {
        if (curr == target) {
            ans.add(new ArrayList<Integer>(combo));
            return;
        } else if (i >= candidates.length || curr > target) {
            return;
        }

        combo.add(candidates[i]);
        dfs(i, curr + candidates[i], combo);

        combo.remove(combo.size() - 1);
        dfs(i + 1, curr, combo);
    }
}
