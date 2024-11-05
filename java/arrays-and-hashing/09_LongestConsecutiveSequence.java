class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> unique = new HashSet<>();
        for (int num : nums) {
            unique.add(num);
        }

        int res = 0;
        for (int num : nums) {
            // Check if this num is the start of a sequence
            if (!unique.contains(num - 1)) {
                int len = 0;
                int temp = num;
                while (unique.contains(temp)) {
                    ++len;
                    ++temp;
                }

                res = Math.max(len, res);
            }
        }

        return res;
    }
}
