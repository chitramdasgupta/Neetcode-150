package Stack;

// Space - O(n)
public class MinStack {
    private Node head;

    public MinStack() {
    }

    // Time - O(1)
    public void push(int val) {
        if (head == null) {
            head = new Node(val, val, null);
        } else {
            head = new Node(val, Math.min(val, head.min), head);
        }
    }

    // Time - O(1)
    public void pop() {
        head = head.next;
    }

    // Time - O(1)
    public int top() {
        return head.val;
    }

    // Time - O(1)
    public int getMin() {
        return head.min;
    }

    private class Node {
        int val;
        int min;
        Node next;

        Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}