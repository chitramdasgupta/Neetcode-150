package Tree;

import java.util.Stack;

// Time - O(n)
// Space - O(n)
public class KthSmallestElementInABST {
    public int kthSmallest(TreeNode root, int k) {
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();

        // Iterative inorder traversal
        // Remember inorder traversal of a BST returns the elements in ascending order
        while(curr != null || !stack.isEmpty()) {
            // We try to reach as far left as we can
            while(curr != null) {
                stack.add(curr);
                curr = curr.left;
            }
            // process the leftmost node at this level
            curr = stack.pop();
            k -= 1;
            if(k == 0) {
                break;
            }
            // Then we try to do the same for the right node
            curr = curr.right;
        }

        return curr.val;
    }
}
