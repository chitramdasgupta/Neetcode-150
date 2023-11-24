package Stack;

import java.util.Stack;

// Time - O(n)
// Space - O(n)
// This is a good introduction to the usage of monotonic stack--a stack with
// non-decreasing or non-increasing values only
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];

        Stack<Integer> stack = new Stack<Integer>();
        // i is the currentDay
        for(int i = 0; i < temperatures.length; ++i) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int pastDay = stack.pop();
                ans[pastDay] = i - pastDay;
            }
            stack.add(i);
        }

        return ans;
    }
}
