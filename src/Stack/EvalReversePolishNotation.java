package Stack;

import java.util.Stack;

// Time - O(n)
// Space - O(n)
public class EvalReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> operands = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    int second = operands.pop();
                    int first = operands.pop();
                    operands.push(first + second);
                }
                case "-" -> {
                    int second = operands.pop();
                    int first = operands.pop();
                    operands.push(first - second);
                }
                case "*" -> {
                    int second = operands.pop();
                    int first = operands.pop();
                    operands.push(first * second);
                }
                case "/" -> {
                    int second = operands.pop();
                    int first = operands.pop();
                    operands.push(first / second);
                }
                default -> operands.push(Integer.parseInt(token));
            }
        }

        return operands.pop();
    }
}
