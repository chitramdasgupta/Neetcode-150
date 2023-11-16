package Stack;

import java.util.ArrayList;
import java.util.List;

// Complexity analysis: https://redquark.org/leetcode/0022-generate-parentheses/
// Time - O(4^n / sqrt(n))
// Space - O(4^n / sqrt(n))
// Here StringBuilder temp acts as a stack
public class GenerateParentheses {
    List<String> ans;

    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        backtrack(0, 0, new StringBuilder(), n);
        return ans;
    }

    private void backtrack(int opened, int closed, StringBuilder temp, int n) {
        if (opened == closed && opened == n) {
            ans.add(temp.toString());
        }

        if (opened < n) {
            temp.append("(");
            backtrack(opened + 1, closed, temp, n);
            temp.deleteCharAt(temp.length() - 1);
        }

        if (closed < opened) {
            temp.append(")");
            backtrack(opened, closed + 1, temp, n);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
