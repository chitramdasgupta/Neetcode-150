package Tree;

// Time - O(n)
// Space - O(n)
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    boolean helper(TreeNode node, Integer left, Integer right) {
        if (node == null) {
            return true;
        }

        if ((right != null && node.val >= right) ||
                (left != null && node.val <= left)) {
            return false;
        }

        return helper(node.left, left, node.val) && helper(node.right, node.val, right);
    }
}
