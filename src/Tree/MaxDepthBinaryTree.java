package Tree;

// Time - O(n)
// Space - O(n)
public class MaxDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        return calculateDepth(root);
    }

    int calculateDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = calculateDepth(root.left);
        int rightDepth = calculateDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
