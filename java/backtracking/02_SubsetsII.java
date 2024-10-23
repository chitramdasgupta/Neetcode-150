class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> curr = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, curr, 0);

        return res;
    }

    private void backtrack(int[] nums, List<Integer> curr, int start) {
      res.add(new ArrayList<>(curr));

      for (int i = start; i < nums.length; ++i) {
        if (i > start && nums[i] == nums[i - 1]) {
          continue;
        }

        curr.add(nums[i]);
        backtrack(nums, curr, i + 1);
        curr.removeLast();
      }
    }
}
