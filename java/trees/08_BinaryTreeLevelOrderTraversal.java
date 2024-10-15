class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int len = q.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < len; ++i) {
                TreeNode node = q.poll();

                level.add(node.val);

                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }

            res.add(level);
        }

        return res;
    }
}
