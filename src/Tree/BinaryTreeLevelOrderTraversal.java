package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Level Order Traversal is also called Breadth First Search
// Time - O(n)
// Space - O(n)
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();

        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            List<Integer> currLevel = new ArrayList<>();
            int count = queue.size();

            for (int i = 0; i < count; ++i) {
                TreeNode temp = queue.remove();
                currLevel.add(temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            ans.add(currLevel);
        }

        return ans;
    }
}
