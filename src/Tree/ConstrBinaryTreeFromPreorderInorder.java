package Tree;

// Time - O(n)
// Space - O(n)
public class ConstrBinaryTreeFromPreorderInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, 0, inorder.length - 1);
    }

    // idx is the current index in the preorder array--idx is the current root.
    // Left is the start and right is the end of the inorder array in consideration
    // for the current iteration, and the elements within this range form a part of
    // the current subtree.
    TreeNode helper(int[] preorder, int[] inorder, int idx, int left, int right) {
        if (idx >= inorder.length || left > right) {
            return null;
        }

        int currVal = preorder[idx];
        TreeNode node = new TreeNode(currVal);

        // The position of the root in the inorder traversal
        // The elements to the left of this position belong to the left subtree
        // Likewise, for the elements to the right.
        int pos = 0;
        for (int i = 0; i < inorder.length; ++i) {
            if (currVal == inorder[i]) {
                pos = i;
                break;
            }
        }

        // The next element in preorder is the root of the left subtree
        node.left = helper(preorder, inorder, idx + 1, left, pos - 1);
        // The next element after the left subtree elements is the root of the right subtree
        // `pos - left` is the number of elements in the left subtree; `+1` for the next element
        node.right = helper(preorder, inorder, idx + pos - left + 1, pos + 1, right);
        return node;
    }
}
