class Solution {
    private boolean res = true;

    public boolean isBalanced(TreeNode root) {
        dfs(root);

        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        if (Math.abs(left - right) > 1) {
            res = false;
        }

        return 1 + Math.max(left, right);
    }
}
