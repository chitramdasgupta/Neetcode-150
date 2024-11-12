# Reverse Linked List

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }
}
```

# Merge Two Sorted Lists

```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                curr.next = new ListNode(list2.val);
                list2 = list2.next;
            }

            curr = curr.next;
        }

        while (list1 != null) {
            curr.next = new ListNode(list1.val);

            list1 = list1.next;
            curr = curr.next;
        }

        while (list2 != null) {
            curr.next = new ListNode(list2.val);

            list2 = list2.next;
            curr = curr.next;
        }

        return dummy.next;
    }
}
```

# Linked List Cycle

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        boolean res = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                res = true;
                break;
            }
        }

        return res;
    }
}
```

# Reorder List

```java
class Solution {
    public void reorderList(ListNode head) {
        // Find middle of linked list
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // Slow is now the at the middle of the linked list

        // Reverse the portion after the middle
        ListNode prev = null;
        ListNode cur = slow.next;
        while (cur != null) {
            ListNode next = cur.next;

            cur.next = prev;

            prev = cur;
            cur = next;
        }

        // Detach the list into two
        slow.next = null;

        // Merge the two halves
        ListNode first = head;
        ListNode second = prev;
        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;

            first.next = second;
            second.next = temp1;

            first = temp1;
            second = temp2;
        }
    }
}
```

# Remove Nth Node From End Of List

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = head;

        while (n > 0) {
            fast = fast.next;
            --n;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }
}
```

# Copy List With Random Pointer

```java
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> oldToNew = new HashMap<>();

        Node current = head;
        while (current != null) {
            oldToNew.put(current, new Node(current.val));

            current = current.next;
        }

        current = head;
        while (current != null) {
            Node newNode = oldToNew.get(current);
            newNode.next = oldToNew.get(current.next);
            newNode.random = oldToNew.get(current.random);

            current = current.next;
        }

        return oldToNew.get(head);
    }
}
```

# Add Two Numbers

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null || l2 != null || carry > 0) {
            int first = l1 == null ? 0 : l1.val;
            int second = l2 == null ? 0 : l2.val;

            int sum = first + second + carry;
            carry = sum / 10;

            cur.next = new ListNode(sum % 10);
            cur = cur.next;

            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }

        return dummy.next;
    }
}
```

# Find The Duplicate Number

```java
class Solution {
    public int findDuplicate(int[] nums) {
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
}
```

# Lru Cache

```java
class LRUCache {
    private final Node left;
    private final Node right;

    private final Map<Integer, Node> cache;
    private final int capacity;

    public LRUCache(int capacity) {
        left = new Node(-1, -1);
        right = new Node(-1, -1);
        left.next = right;
        right.prev = left;

        cache = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);
        remove(node);
        insert(node);

        return node.val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }

        cache.put(key, new Node(key, value));
        insert(cache.get(key));

        if (cache.size() > capacity) {
            Node lru = left.next;

            remove(lru);
            cache.remove(lru.key);
        }
    }

    private void insert(Node node) {
        Node prev = right.prev;
        Node next = right;

        prev.next = node;
        next.prev = node;

        node.next = next;
        node.prev = prev;
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private static class Node {
        int key;
        int val;
        Node next;
        Node prev;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
```

