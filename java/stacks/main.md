# Valid Parentheses

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }

            char top = stack.pop();
            if ((c == ')' && top != '(') || (c == '}' && top != '{')
                || (c == ']' && top != '[')) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
```

# Min Stack

```java
class MinStack {
    private Node head;

    public MinStack() { }

    public void push(int val) {
        if (head == null) {
            head = new Node(val, null, val);
            return;
        }

        head = new Node(val, head, Math.min(val, head.minVal));
    }

    public void pop() {
        if (head == null) {
            return;
        }

        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.minVal;
    }

    private record Node(int val, Node next, int minVal) {}
}
```

# Evaluate Reverse Polish Notation

```java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Set<String> operators = Set.of("+", "-", "*", "/");
        for (String token : tokens) {
            if (operators.contains(token)) {
                int second = stack.pop();
                int first = stack.pop();

                int newVal = switch (token) {
                    case "+" -> first + second;
                    case "-" -> first - second;
                    case "*" -> first * second;
                    case "/" -> first / second;
                    default -> 0;
                };

                stack.push(newVal);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.peek();
    }
}
```

# Generate Parentheses

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    private final List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        backtrack(n, 0, 0, new StringBuilder());
        return res;
    }

    private void backtrack(int n, int open, int closed, StringBuilder curr) {
        if (open == closed && open == n) {
            res.add(curr.toString());
            return;
        }

        if (open < n) {
            curr.append("(");
            backtrack(n, open + 1, closed, curr);
            curr.deleteCharAt(curr.length() - 1);
        }

        if (closed < open) {
            curr.append(")");
            backtrack(n, open, closed + 1, curr);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}
```

# Daily Temperatures

```java
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
```

# Car Fleet

```java
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int len = position.length;
        double[][] cars = new double[len][2];
        for (int i = 0; i < len; ++i) {
            cars[i][0] = position[i];
            cars[i][1] = (target - position[i]) / (double) speed[i];
        }

        Arrays.sort(cars, Comparator.comparingDouble(a -> a[0]));
        double prevTime = 0;
        int res = 0;

        for (int i = len - 1; i >= 0; --i) {
            double currentTime = cars[i][1];

            if (currentTime > prevTime) {
                ++res;
                prevTime = currentTime;
            }
        }

        return res;
    }
}
```

# Largest Rectangle In Histogram

```java
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
```

