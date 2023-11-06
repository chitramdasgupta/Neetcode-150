package LinkedList;

// Time - O(n)
// Space - O(1)
class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode curr = new ListNode(-101);
        ListNode newHead = curr;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        while (list1 != null) {
            curr.next = list1;
            curr = curr.next;
            list1 = list1.next;
        }
        while (list2 != null) {
            curr.next = list2;
            curr = curr.next;
            list2 = list2.next;
        }
        return newHead.next;
    }
}
