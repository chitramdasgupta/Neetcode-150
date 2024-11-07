class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<int[]> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; ++i) {
            while (!stack.isEmpty() && temperatures[i] > stack.peek()[0]) {
                int[] temp = stack.pop();
                int prevIdx = temp[1];

                res[prevIdx] = i - prevIdx;
            }

            stack.push(new int[] {temperatures[i], i});
        }

        return res;
    }
}
