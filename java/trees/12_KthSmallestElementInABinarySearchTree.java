class Solution {
    private int count;
    private int res;

    public int kthSmallest(TreeNode root, int k) {
        count = k;

        dfs(root, k);

        return res;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        dfs(root.left, k);

        --count;
        if (count == 0) {
            res = root.val;
        }

        dfs(root.right, k);
    }
}
