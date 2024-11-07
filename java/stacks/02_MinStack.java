class MinStack {
    private Node head;

    public MinStack() { }

    public void push(int val) {
        if (head == null) {
            head = new Node(val, null, val);
            return;
        }

        head = new Node(val, head, Math.min(val, head.minVal));
    }

    public void pop() {
        if (head == null) {
            return;
        }

        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.minVal;
    }

    private record Node(int val, Node next, int minVal) {}
}
