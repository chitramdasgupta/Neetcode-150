class Solution {
    public int trap(int[] height) {
        if (height.length <= 2) return 0;  // Need at least 3 bars to trap water

        int n = height.length;
        int[] maxLeftHeight = new int[n];
        int[] maxRightHeight = new int[n];

        // Maximum left height at current index
        maxLeftHeight[0] = 0;
        for (int i = 1; i < n; i++) {
            maxLeftHeight[i] = Math.max(maxLeftHeight[i-1], height[i-1]);
        }

        // Maximum right height at current index
        maxRightHeight[n-1] = 0;
        for (int i = n-2; i >= 0; i--) {
            maxRightHeight[i] = Math.max(maxRightHeight[i+1], height[i+1]);
        }

        int res = 0;
        for (int i = 1; i < n-1; i++) {
            int curr = Math.min(maxLeftHeight[i], maxRightHeight[i]) - height[i];
            res = Math.max(curr, 0);
        }

        return res;
    }
}
