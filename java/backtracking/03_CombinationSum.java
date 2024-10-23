class Solution {
  private List<List<Integer>> res = new ArrayList<>();

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<Integer> curr = new ArrayList<>();
    backtrack(candidates, curr, target, 0);

    return res;
  }

  private void backtrack(int[] candidates, List<Integer> curr, int target, int start) {
    if (target < 0) {
      return;
    }
    if (target == 0) {
      res.add(new ArrayList<>(curr));
      return;
    }

    for (int i = start; i < candidates.length; ++i) {
      curr.add(candidates[i]);
      backtrack(candidates, curr, target - candidates[i], i);
      curr.removeLast();
    }
  }
}
