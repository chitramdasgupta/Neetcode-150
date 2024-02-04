1. Reverse Linked List

```cpp
class Solution {
public:
  ListNode *reverseList(ListNode *head) {
    ListNode *prev = nullptr;
    ListNode *curr = head;

    while (curr != nullptr) {
      ListNode *next = curr->next;
      curr->next = prev;

      prev = curr;
      curr = next;
    }

    return prev;
  }
};
```

2. Merge Two Sorted Lists

```cpp
class Solution {
public:
  ListNode *mergeTwoLists(ListNode *list1, ListNode *list2) {
    ListNode *head = new ListNode(INT_MIN);
    ListNode *curr = head;
    while (list1 != nullptr && list2 != nullptr) {
      if (list1->val < list2->val) {
        curr->next = new ListNode(list1->val);
        list1 = list1->next;
      } else {
        curr->next = new ListNode(list2->val);
        list2 = list2->next;
      }

      curr = curr->next;
    }

    while (list1 != nullptr) {
      curr->next = new ListNode(list1->val);

      list1 = list1->next;
      curr = curr->next;
    }

    while (list2 != nullptr) {
      curr->next = new ListNode(list2->val);

      list2 = list2->next;
      curr = curr->next;
    }

    return head->next;
  }
};
```

3. Linked List Cycle

```cpp
 */
class Solution {
public:
  bool hasCycle(ListNode *head) {
    ListNode *slow = head;
    ListNode *fast = head;

    while (fast != nullptr && fast->next != nullptr) {
      slow = slow->next;
      fast = fast->next->next;

      if (slow == fast) {
        return true;
      }
    }

    return false;
  }
};
```

4. Reorder List

```cpp
class Solution {
public:
  void reorderList(ListNode *head) {
    if (head == nullptr || head->next == nullptr) {
      return;
    }

    // Find middle node
    ListNode *slow = head;
    ListNode *fast = head;

    while (fast != nullptr && fast->next != nullptr) {
      slow = slow->next;
      fast = fast->next->next;
    }

    // slow points to the middle of the linked list
    // reverse the second half of the list
    ListNode *prev = nullptr;
    ListNode *curr = slow->next;
    while (curr != nullptr) {
      ListNode *next = curr->next;
      curr->next = prev;

      prev = curr;
      curr = next;
    }
    // deatch the second list so that no cycles arise
    slow->next = nullptr;

    // Finally merge the two lists
    ListNode *first = head;
    ListNode *second = prev;
    while (second != nullptr) {
      ListNode *temp1 = first->next;
      ListNode *temp2 = second->next;

      first->next = second;
      second->next = temp1;

      first = temp1;
      second = temp2;
    }
  }
};
```

5. Remove Nth Node From End of List

```cpp
class Solution {
public:
  ListNode *removeNthFromEnd(ListNode *head, int n) {
    ListNode *dummy = new ListNode(INT_MIN, head);
    ListNode *slow = dummy;
    ListNode *fast = head;

    while (n > 0) {
      fast = fast->next;
      --n;
    }

    while (fast != nullptr) {
      slow = slow->next;
      fast = fast->next;
    }

    slow->next = slow->next->next;

    return dummy->next;
  }
};
```

6. Copy List With Random Pointer

```cpp
class Solution {
public:
  Node *copyRandomList(Node *head) {
    unordered_map<Node *, Node *> nodeMap;

    // Create a hashmap of the old nodes mapped to the new nodes
    Node *curr = head;
    while(curr != nullptr) {
      nodeMap[curr] = new Node(curr->val);

      curr = curr->next;
    }

    curr = head;
    while (curr != nullptr) {
      Node *newNode = nodeMap[curr];
      newNode->next = nodeMap[curr->next];
      newNode->random = nodeMap[curr->random];

      curr = curr->next;
    }

    return nodeMap[head];
  }
};
```

7. Add Two Numbers

```cpp
class Solution {
public:
  ListNode *addTwoNumbers(ListNode *l1, ListNode *l2) {
    ListNode *dummy = new ListNode(INT_MIN);
    ListNode *curr = dummy;

    int carry = 0;
    while (l1 != nullptr || l2 != nullptr || carry != 0) {
      int val1 = l1 != nullptr ? l1->val : 0;
      int val2 = l2 != nullptr ? l2->val : 0;

      int temp = val1 + val2 + carry;
      curr->next = new ListNode(temp % 10);
      carry = temp / 10;

      l1 = l1 != nullptr ? l1->next : l1;
      l2 = l2 != nullptr ? l2->next : l2;
      curr = curr->next;
    }

    return dummy->next;
  }
};
```

8. Find the Duplicate Number

```cpp
class Solution {
public:
  int findDuplicate(vector<int> &nums) {
    int slow = nums[0];
    int fast = nums[nums[0]];

    while (slow != fast) {
      slow = nums[slow];
      fast = nums[nums[fast]];
    }

    slow = 0;
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[fast];
    }

    return slow;
  }
};
```

9. LRU Cache

The cache will be represented as a doubly linked list (with both key, and val).
There will be a dummy LRU node and a dummy MRU node in the left and right,
respectively.

We will also use a hashmap mapping the key to a node to check the existence of
keys, and direct access to the nodes.

```cpp
// This represents the elements of the cache
class Node {
public:
  int key;
  int val;
  Node *next;
  Node *prev;

  Node(int key, int val) {
    this->key = key;
    this->val = val;
  }
};

class LRUCache {
public:
  LRUCache(int capacity) {
    this->capacity = capacity;

    left = new Node(-1, -1);
    right = new Node(-1, -1);

    left->next = right;
    right->prev = left;
  }

  int get(int key) {
    if (!cache.contains(key)) {
      return -1;
    }

    remove(cache[key]);
    insert(cache[key]);

    return cache[key]->val;
  }

  void put(int key, int value) {
    if (cache.contains(key)) {
      remove(cache[key]);
    }

    cache[key] = new Node(key, value);
    insert(cache[key]);

    if (cache.size() > capacity) {
      Node *lru = left->next;
      remove(lru);
      cache.erase(lru->key);
      delete lru;
    }
  }

private:
  unordered_map<int, Node *> cache; // {key, Node *}
  int capacity;
  Node *left; // LRU
  Node *right; // MRU

  void insert(Node *node) {
    Node *prev = right->prev;
    Node *next = right;

    // Place the node in the cache
    prev->next = node;
    next->prev = node;

    // Modify the pointers of the node
    node->next = next;
    node->prev = prev;
  }

  void remove(Node *node) {
    Node *prev = node->prev;
    Node *next = node->next;

    prev->next = next;
    next->prev = prev;
  }
};
```
