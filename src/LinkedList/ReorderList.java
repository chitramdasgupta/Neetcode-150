package LinkedList;

// Time - O(n)
// Space - O(1)
public class ReorderList {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) {
            return;
        }

        // Finding the middle of the list
        ListNode slow = head; // This will become the middle
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Reversing the list after the middle
        ListNode prev = null;
        ListNode curr = slow.next;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        slow.next = null; // Disconnect the two halves to prevent cycles

        // Merge the two halves
        ListNode first = head;
        ListNode second = prev;
        while(second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;

            first.next = second;
            second.next = temp1;

            first = temp1;
            second = temp2;
        }
    }
}
