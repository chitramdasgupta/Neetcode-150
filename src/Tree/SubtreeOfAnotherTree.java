package Tree;

// Time - O(n)
// Space - O(n)
public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }

        if (root == null) {
            return false;
        }

        if (subRoot == null) {
            return true;
        }

        if (isSameTree(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
