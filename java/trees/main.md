# Invert Binary Tree

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
```

# Maximum Depth Of Binary Tree

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return 1 + Math.max(left, right);
    }
}
```

# Diameter Of Binary Tree

```java
class Solution {
    private int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        res = Math.max(res, left + right);

        return 1 + Math.max(left, right);
    }
}
```

# Balanced Binary Tree

```java
class Solution {
    private boolean res = true;

    public boolean isBalanced(TreeNode root) {
        dfs(root);

        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        if (Math.abs(left - right) > 1) {
            res = false;
        }

        return 1 + Math.max(left, right);
    }
}
```

# Same Tree

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```

# Subtree Of Another Tree

```java
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }

        if (isSameTree(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```

# Lowest Common Ancestor Of A Binary Search Tree

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if(p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }
}
```

# Binary Tree Level Order Traversal

```java
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
```

# Binary Tree Right Side View

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; ++i) {
                TreeNode node = q.poll();

                if (i == len - 1) {
                    res.add(node.val);
                }

                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }

        return res;
    }
}
```

# Count Good Nodes In A Binary Tree

```java
class Solution {
    private int res = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return res;
    }

    private void dfs(TreeNode root, int maxVal) {
        if (root == null) {
            return;
        }

        if (root.val >= maxVal) {
            ++res;
        }

        dfs(root.left, Math.max(root.val, maxVal));
        dfs(root.right, Math.max(root.val, maxVal));
    }
}

```

# Validate Binary Search Tree

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long left, long right) {
        if (root == null) {
            return true;
        }

        if (root.val <= left || root.val >= right) {
            return false;
        }

        return dfs(root.left, left, root.val) &&
               dfs(root.right, root.val, right);
    }
}
```

# Kth Smallest Element In A Binary Search Tree

```java
class Solution {
    private int count;
    private int res;

    public int kthSmallest(TreeNode root, int k) {
        count = k;

        dfs(root, k);

        return res;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        dfs(root.left, k);

        --count;
        if (count == 0) {
            res = root.val;
        }

        dfs(root.right, k);
    }
}
```

# Construct Binary Tree From Preorder And Inorder Traversal

```java
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
```

