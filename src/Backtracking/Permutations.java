package Backtracking;

import java.util.ArrayList;
import java.util.List;

// Time - O(n!)
// Space - O(n)
public class Permutations {
    List<List<Integer>> ans;
    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        backtrack(nums, 0);
        return ans;
    }

    public void backtrack(int[] nums, int idx) {
        if (idx == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int num : nums) temp.add(num);
            ans.add(temp);
            return;
        }

        for (int i = idx; i < nums.length; ++i) {
            swap(nums, idx, i);
            backtrack(nums, idx + 1);
            swap(nums, idx, i);
        }
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /*
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if (nums.length == 1) {
            res.add(new ArrayList<>(Arrays.asList(nums[0])));
            return res;
        }

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];

            int[] newArr = new int[nums.length - 1];
            System.arraycopy(nums, 0, newArr, 0, i);
            System.arraycopy(nums, i + 1, newArr, i, nums.length - i - 1);

            List<List<Integer>> perms = permute(newArr);

            for (List<Integer> perm : perms) {
                List<Integer> newPerm = new ArrayList<>(perm);
                newPerm.add(n);
                res.add(newPerm);
            }
        }

        return res;
    }
    */
}
