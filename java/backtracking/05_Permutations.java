class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums, new ArrayList<>());

        return res;
    }

    private void backtrack(int[] nums, List<Integer> curr) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (curr.contains(nums[i])) {
                continue;
            }

            curr.add(nums[i]);
            backtrack(nums, curr);
            curr.removeLast();
        }
    }
}
