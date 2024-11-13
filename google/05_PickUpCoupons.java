class Solution {
    public int minCostToWin(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        List<Integer> window = new ArrayList<>();
        int res = Integer.MAX_VALUE;
        for (int num : nums) {
            if (seen.contains(num)) {
                while (seen.contains(num)) {
                    int temp = window.getFirst();
                    window.removeFirst();
                    seen.remove(temp);
                }

                res = Math.min(window.size() + 2, res);
            }

            window.add(num);
            seen.add(num);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
