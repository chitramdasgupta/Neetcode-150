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
