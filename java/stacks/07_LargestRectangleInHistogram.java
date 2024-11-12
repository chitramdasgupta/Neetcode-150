class Solution {
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        Stack<int[]> stack = new Stack<>();

        // Process all bars, including one dummy bar at the end
        for (int i = 0; i <= heights.length; i++) {
            int currHeight = (i == heights.length) ? 0 : heights[i];

            while (!stack.isEmpty() && currHeight < stack.peek()[1]) {
                int[] temp = stack.pop();
                int height = temp[1];
                // When we pop off the last height, it can extend the entire length of the array
                // The - 1 is required because we are calculating the gap between the two indices (excluding)
                int width = stack.isEmpty() ? i : i - stack.peek()[0] - 1;

                res = Math.max(res, height * width);
            }

            stack.push(new int[] {i, currHeight});
        }

        return res;
    }
}
