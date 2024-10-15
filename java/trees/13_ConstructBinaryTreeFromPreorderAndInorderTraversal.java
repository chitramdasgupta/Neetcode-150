class Solution {
    private int[] preorder;
    private int[] inorder;
    private Map<Integer, Integer> inorderMap = new HashMap<>();
    private int rootIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        for (int i = 0; i < inorder.length; ++i) {
            inorderMap.put(inorder[i], i);
        }

        return helper(0, preorder.length - 1);
    }

    // left and right represent the current subarray of inorder being processed
    private TreeNode helper(int left, int right) {
        if (left > right) {
            return null;
        }

        // Create a new node from the first unprocessed value in preorder
        TreeNode root = new TreeNode(preorder[rootIndex]);
        ++rootIndex;

        // The split in the inorder traversal for the current node
        int pos = inorderMap.get(root.val);

        // The left subtree will consist of the values to the left of the split
        // Likewise, for the right subtree.
        root.left = helper(left, pos - 1);
        root.right = helper(pos + 1, right);

        return root;
    }
}
