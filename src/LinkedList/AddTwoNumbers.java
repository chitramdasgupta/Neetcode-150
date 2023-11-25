package LinkedList;

// Time - O(max(m, n))
// Space - O(max(m, n))
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curr = new ListNode(-1);
        ListNode dummy = curr;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int first = l1 != null ? l1.val : 0;
            int second = l2 != null ? l2.val : 0;

            int newVal = (first + second + carry) % 10;
            carry = (first + second + carry) / 10;

            curr.next = new ListNode(newVal);
            curr = curr.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }
}
