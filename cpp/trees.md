1. Lowest Common Ancestor of a Binary Search Tree

This is a BST so the lowest common ancestor will be where there is a 'split', that is
one of the node is the left subtree and the other in the right subtree; or, if the current
node is equal to one of the given nodes, then the current node is the LCA.

```cpp
class Solution {
public:
  TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
    // Return the root if there is a split, or if the current node is equal to
    // either of the given nodes
    if ((p->val < root->val && q->val > root->val) ||
        (p->val > root->val && q->val < root->val) ||
        (p->val == root->val || q->val == root->val)) {
      return root;
    }

    // if both the nodes are in the right subtree then LCA must in the right
    // subtree else the LCA must be in the left subtree
    if (p->val > root->val && q->val > root.val) {
      return lowestCommonAncestor(root->right, p, q);
    } else {
      return lowestCommonAncestor(root->left, p, q);
    }
  }
};
```

2. Invert Binary Tree

```cpp
class Solution {
public:
  TreeNode* invertTree(TreeNode* root) {
    if (root == nullptr) {
      return nullptr;
    }

    TreeNode* temp = root->left;
    root->left = root->right;
    root->right = temp;

    invertTree(root->left);
    invertTree(root->right);

    return root;
  }
};
```

3. Binary Tree Level Order Traversal

```cpp
class Solution {
public:
  vector<vector<int>> levelOrder(TreeNode* root) {
    vector<vector<int>> ans = {};
    if (root == nullptr) {
      return ans;
    }

    queue<TreeNode*> visited{{root}};
    while (!visited.empty()) {
      int len = visited.size();
      vector<int> current = {};
      for (int i = 0; i < len; ++i) {
        TreeNode* node = visited.front();
        visited.pop();

        current.push_back(node->val);

        if (node->left != nullptr) {
          visited.push(node->left);
        }
        if (node->right != nullptr) {
          visited.push(node->right);
        }
      }
      ans.push_back(current);
    }

    return ans;
  }
};
```

4. Maximum Depth of Binary Tree

```cpp
class Solution {
public:
  int maxDepth(TreeNode* root) {
    if (root == nullptr) {
      return 0;
    }

    return 1 + max(maxDepth(root->left), maxDepth(root->right));
  }
};
```

5. Binary Tree Right Side View

```cpp
class Solution {
public:
  vector<int> rightSideView(TreeNode* root) {
    vector<int> ans = {};
    if (root == nullptr) {
      return ans;
    }

    queue<TreeNode*> visit{{root}};
    while (!visit.empty()) {
      int len = visit.size();
      for (int i = 0; i < len; ++i) {
        TreeNode* node = visit.front();
        if (node->left != nullptr) {
          visit.push(node->left);
        }
        if (node->right != nullptr) {
          visit.push(node->right);
        }

        if (i == len - 1) {
          ans.push_back(node->val);
        }

        visit.pop();
      }
    }

    return ans;
  }
};
```

6. Diameter of Binary Tree

We have to keep track of both diameter and maximum depth at each node. We maintain
a reference variable for the diameter, and calculate the `maxDepth` in the recursive
function. The maximum depth is based on the number of nodes; the diameter is based
on the number of edges.

```cpp
class Solution {
public:
  int diameterOfBinaryTree(TreeNode* root) {
    int res = 0;
    dfs(root, res);

    return res;
  }

private:
  int dfs(TreeNode* root, int& res) {
    if (root == nullptr) {
      return 0;
    }

    int left = dfs(root->left, res);
    int right = dfs(root->right, res);

    res = max(res, left + right);

    return 1 + max(left, right);
  }
};
```

7. Count Good Nodes in Binary Tree

```cpp
class Solution {
public:
  int goodNodes(TreeNode *root) { return calc_good_nodes(root, INT_MIN); }

private:
  int calc_good_nodes(TreeNode *root, int max_so_far) {
    if (root == nullptr) {
      return 0;
    }

    int res = 0;
    if (root->val >= max_so_far) {
      res += 1;
    }

    res += calc_good_nodes(root->left, max(max_so_far, root->val));
    res += calc_good_nodes(root->right, max(max_so_far, root->val));

    return res;
  }
};
```

8. Balanced Binary Tree

```cpp
class Solution {
public:
  bool isBalanced(TreeNode *root) {
    bool res = true;
    dfs(root, res);
    return res;
  }

private:
  int dfs(TreeNode *root, bool &res) {
    if (root == nullptr) {
      return 0;
    }

    int leftHeight = dfs(root->left, res);
    int rightHeight = dfs(root->right, res);

    if (abs(leftHeight - rightHeight) > 1) {
      res = false;
    }

    return 1 + max(leftHeight, rightHeight);
  }
};
```

9. Validate Binary Search Tree

```cpp
class Solution {
public:
  bool isValidBST(TreeNode *root) { return helper(root, LONG_MIN, LONG_MAX); }

private:
  bool helper(TreeNode *root, long left, long right) {
    if (root == nullptr) {
      return true;
    }

    if (right <= root->val || left >= root->val) {
      return false;
    }

    return helper(root->left, left, root->val) &&
           helper(root->right, root->val, right);
  }
};
```

10. Same Tree

```cpp
class Solution {
public:
  bool isSameTree(TreeNode *p, TreeNode *q) {
    if (p == nullptr && q == nullptr) {
      return true;
    }

    if ((p == nullptr && q != nullptr) || (p != nullptr && q == nullptr) ||
        (p->val != q->val)) {
      return false;
    }

    return isSameTree(p->left, q->left) && isSameTree(p->right, q->right);
  }
};
```

11. Kth Smallest Element in a BST

```cpp
class Solution {
public:
  int kthSmallest(TreeNode *root, int k) {
    int res = 0;
    inorder(root, k, res);
    return res;
  }

private:
  void inorder(TreeNode *root, int &k, int &res) {
    if (root == nullptr) {
      return;
    }

    inorder(root->left, k, res);

    --k;
    if (k == 0) {
      res = root->val;
      return;
    }

    inorder(root->right, k, res);
  }
};
```

12. Subtree of Another Tree

```cpp
class Solution {
public:
  bool isSubtree(TreeNode *root, TreeNode *subRoot) {
    if (root == nullptr) {
      return false;
    }

    if (isSameTree(root, subRoot)) {
      return true;
    }

    return isSubtree(root->left, subRoot) || isSubtree(root->right, subRoot);
  }

  bool isSameTree(TreeNode *root, TreeNode *subRoot) {
    if (root == nullptr && subRoot == nullptr) {
      return true;
    }

    if ((root == nullptr && subRoot != nullptr) ||
        (root != nullptr && subRoot == nullptr) ||
        (root->val != subRoot->val)) {
      return false;
    }

    return isSameTree(root->left, subRoot->left) &&
           isSameTree(root->right, subRoot->right);
  }
};
```

13. Construct Binary Tree from Preorder and Inorder Traversal

```cpp
class Solution {
public:
  TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder) {
    int index = 0;
    return build(preorder, inorder, index, 0, inorder.size() - 1);
  }

private:
  TreeNode *build(vector<int> &preorder, vector<int> &inorder, int &index,
                  int left, int right) {
    if (left > right) {
      return nullptr;
    }

    TreeNode *root = new TreeNode(preorder[index]);

    int split =
        std::find(inorder.begin(), inorder.end(), root->val) - inorder.begin();

    ++index;

    root->left = build(preorder, inorder, index, left, split - 1);
    root->right = build(preorder, inorder, index, split + 1, right);

    return root;
  }
};
```
