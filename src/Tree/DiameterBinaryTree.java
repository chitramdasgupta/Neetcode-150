package Tree;

// Time - O(n)
// Space - O(n)
public class DiameterBinaryTree {
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        calculateNumEdges(root);
        return diameter;
    }

    int calculateNumEdges(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftNumEdges = calculateNumEdges(root.left);
        int rightNumEdges = calculateNumEdges(root.right);

        diameter = Math.max(diameter, leftNumEdges + rightNumEdges);

        return Math.max(leftNumEdges, rightNumEdges) + 1;
    }
}
