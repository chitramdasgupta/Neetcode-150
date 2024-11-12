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
