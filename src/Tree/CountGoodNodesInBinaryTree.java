package Tree;

// Time - O(n)
// Space - O(n)
public class CountGoodNodesInBinaryTree {
    private int res;
    public int goodNodes(TreeNode root) {
        helper(root, root.val);
        return res;
    }

    // Here maxVal is the maximum value encountered so far
    void helper(TreeNode node, int maxVal) {
        if (node == null) {
            return;
        }

        if (node.val >= maxVal) {
            res += 1;
        }

        helper(node.left, Math.max(node.val, maxVal));
        helper(node.right, Math.max(node.val, maxVal));
    }
}
