package Tree;

// Time - O(n)
// Space - O(n)
public class BalancedBinaryTree {
    private boolean answer = true;

    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return answer;
    }

    int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = dfs(root.left);
        int rightDepth = dfs(root.right);

        if (Math.abs(leftDepth - rightDepth) >= 2) {
            answer = false;
        }

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
