# 1. Invert Binary Tree

```python
class Solution:
  def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
    if not root: return None

    root.left, root.right = root.right, root.left

    self.invertTree(root.left)
    self.invertTree(root.right)

    return root
```

```
Time - O(n), where n is the number of nodes in the binary tree
Space - O(h), where h is the height of the binary tree
```

# 2. Maximum Depth of Binary Tree

The maximum depth is given as the number of nodes along the path from the root
to the fathest leaf node

```python
class Solution:
  def maxDepth(self, root: Optional[TreeNode]) -> int:
    if not root: return 0

    leftDepth = self.maxDepth(root.left)
    rightDepth = self.maxDepth(root.right)

    return 1 + max(leftDepth, rightDepth)
```

```
Time - O(n), where n is the number of nodes in the binary tree
Space - O(h), where h is the height of the binary tree
```

# 3. Diamter of Binary Tree

```python
class Solution:
  def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
    res = 0

    def dfs(root):
      if not root: return 0

      leftHeight = dfs(root.left)
      rightHeight = dfs(root.right)

      nonlocal res
      res = max(res, leftHeight + rightHeight)

      return 1 + max(leftHeight, rightHeight)

    dfs(root)
    return res
```

```
Time - O(n), where n is the number of nodes in the binary tree
Space - O(h), where h is the height of the binary tree
```

# 4. Balanced Binary Tree

```python
class Solution:
  def isBalanced(self, root: Optional[TreeNode]) -> bool:
    res = True

    def dfs(root):
      if not root: return 0

      leftHeight = dfs(root.left)
      rightHeight = dfs(root.right)

      nonlocal res
      if abs(leftHeight - rightHeight) > 1: res = False

      return 1 + max(leftHeight, rightHeight)

    dfs(root)
    return res
```


```
Time - O(n), where n is the number of nodes in the binary tree
Space - O(h), where h is the height of the binary tree
```

# 5. Same Tree

Two binary trees are the same if they are structurally identical, and the nodes
have the same value.

```python
class Solution:
  def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
    if not p and not q: return True

    # This conditional succinctly checks if only either of the nodes are null,
    # or if the respective values don't match
    if not p or not q or p.val != q.val: return False

    return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)
```

```
Time - O(n), where n is the minimum number of nodes in either tree
Space - O(h), where h is the minimum height of the two trees
```

# 6. Subtree of Another Tree

```python
class Solution:
  def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
    # This handles the trivial case
    if not subRoot: return True

    if not root: return False

    if self.isSameTree(root, subRoot): return True

    return self.isSubtree(root.left, subRoot) or self.isSubtree(root.right, subRoot)

  def isSameTree(self, p, q):
    if not p and not q: return True

    if not p or not q or p.val != q.val: return False

    return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)
```

```
Time - O(m * n), where m is the number of nodes in the main tree and n is the number of nodes in the subtree
Space - O(h), where h is the height of the main tree
```

# 7. Lowest Common Ancestor of a Binary Search Tree

The LCA of a binary search tree is the closest ancestor of two nodes such that
the two nodes are ancestors of the LCA node. Note that, of the two given nodes
one of them is allowed to be the ancestor of the other.

```python
class Solution:
  def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
    if ((p.val < root.val and q.val > root.val) or
        (p.val > root.val and q.val < root.val) or
        (p.val == root.val) or (q.val == root.val)): return root

    # If we reach here it means that the two given nodes are in the same subtree
    if p.val < root.val and q.val < root.val: return self.lowestCommonAncestor(root.left, p, q)
    if p.val > root.val and q.val > root.val: return self.lowestCommonAncestor(root.right, p, q)
```

```
Time - O(h), where h is the height of the binary search tree
Space - O(h), where h is the height of the binary search tree
```

# 8. Binary Tree Level Order Traversal

This is also called Breadth-First Search (BFS)

```python
class Solution:
  def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
    ans = []
    if not root: return ans

    queue = collections.deque([root])
    while queue:
      curr = []
      length = len(queue)
      for i in range(length):
        node = queue.popleft()
        curr.append(node.val)

        if node.left: queue.append(node.left)
        if node.right: queue.append(node.right)

      ans.append(curr)

    return ans
```

```
Time - O(n), where n is the number of nodes in the binary tree
Space - O(n), where n is the number of nodes in the binary tree
```

# 9. Binary Tree Right Side View

```python
class Solution:
  def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
    ans = []
    if not root: return ans

    queue = collections.deque([root])
    while queue:
      length = len(queue)
      curr = []
      for i in range(length):
        node = queue.popleft()

        if i == length - 1:
          ans.append(node.val)

        if node.left: queue.append(node.left)
        if node.right: queue.append(node.right)

    return ans
```

```
Time - O(n), where n is the number of nodes in the binary tree
Space - O(n), where n is the number of nodes in the binary tree
```

# 10. Count Good Nodes In Binary Tree

A node is said to be good, if in the path from the root to the node there is no node with a value greater than the node.

```python
class Solution:
  def goodNodes(self, root: TreeNode) -> int:
    count = 0
    def dfs(root, maxSoFar):
      if not root: return

      dfs(root.left, max(root.val, maxSoFar))
      dfs(root.right, max(root.val, maxSoFar))

      nonlocal count
      if root.val >= maxSoFar:
        count += 1

    dfs(root, root.val)
    return count
```

```
Time - O(n), where n is the number of nodes in the binary search tree
Space - O(h), where h is the height of the binary search tree

```

# 11. Validate Binary Search Tree

```python
class Solution:
  def isValidBST(self, root: Optional[TreeNode]) -> bool:
    def checkValid(root, minVal, maxVal):
      if not root: return True

      if root.val <= minVal or root.val >= maxVal: return False

      return checkValid(root.left, minVal, root.val) and
             checkValid(root.right, root.val, maxVal)

    return checkValid(root, float('-inf'), float('inf'))
```

```
Time - O(n), where n is the number of nodes in the binary search tree
Space - O(h), where h is the height of the binary search tree
```

# 12. Kth Smallest Element In a Bst

We perform an inorder search and keep track of the decrementing k.

```python
class Solution:
  def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
    stack = []
    curr = root # The current node we are processing

    while stack or curr:
      while curr:
        stack.append(curr)
        curr = curr.left

      curr = stack.pop()

      k -= 1
      if k == 0:
        return curr.val

      curr = curr.right
```

```
Time - O(h + k), where h is the height of the binary search tree and k is the input parameter
Space - O(h), where h is the height of the binary search tree
```

# 13. Construct Binary Tree From Preorder And Inorder Traversal

```python
class Solution:
  def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
    if not preorder or not inorder: return None

    root = TreeNode(preorder[0])

    split = inorder.index(root.val)

    root.left = self.buildTree(preorder[1:split + 1], inorder[:split])
    root.right = self.buildTree(preorder[split + 1:], inorder[split + 1:])

    return root
```

```
Time - O(n), where n is the number of nodes in the binary tree
Space - O(n), where n is the number of nodes in the binary tree
```
