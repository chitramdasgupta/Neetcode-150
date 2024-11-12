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
