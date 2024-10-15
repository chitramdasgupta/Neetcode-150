class Solution {
    private int res = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return res;
    }

    private void dfs(TreeNode root, int maxVal) {
        if (root == null) {
            return;
        }

        if (root.val >= maxVal) {
            ++res;
        }

        dfs(root.left, Math.max(root.val, maxVal));
        dfs(root.right, Math.max(root.val, maxVal));
    }
}

