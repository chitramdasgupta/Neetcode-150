/*
Reservoir sampling:
The first node has a 1/1 probability of being chosen.
The second node has a 1/2 probability of being chosen, making the first node have a probability of 1/2 as well.

This pattern continues, so every node has an equal probability of being the final choice.
*/
class Solution {
    private Random random;
    private ListNode head;

    public Solution(ListNode head) {
        random = new Random();
        this.head = head;
    }

    public int getRandom() {
        ListNode curr = head;
        int res = -1; // the current random value
        int index = 0;
        while (curr != null) {
            int val = random.nextInt(index + 1); // generates a random number from 0 through the current index

            // checks if the current index should replace the previous choice
            if (index == val) {
                res = curr.val;
            }

            ++index;
            curr = curr.next;
        }

        return res;
    }
}
