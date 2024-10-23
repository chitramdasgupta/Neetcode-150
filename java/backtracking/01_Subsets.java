class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
      List<Integer> curr = new ArrayList<>();
      backtrack(nums, curr, 0);

      return res;
    }

    private void backtrack(int[] nums, List<Integer> curr, int start) {
      res.add(new ArrayList<>(curr));

      for (int i = start; i < nums.length; ++i) {
        curr.add(nums[i]);
        backtrack(nums, curr, i + 1);
        curr.removeLast();
      }
    }
}
