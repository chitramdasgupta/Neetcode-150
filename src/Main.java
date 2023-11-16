import Stack.EvalReversePolishNotation;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] input = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(new EvalReversePolishNotation().evalRPN(input));
    }
}
