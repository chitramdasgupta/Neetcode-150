class Solution {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ']') {
                stack.push(s.charAt(i));
            } else {
                StringBuilder sb = new StringBuilder();

                while (stack.peek() != '[') {
                    sb.append(stack.pop());
                }
                stack.pop(); // Remove the '['
                String str = sb.reverse().toString();

                sb.setLength(0); // Clears out the string builder for re-use
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    sb.append(stack.pop());
                }
                int k = Integer.parseInt(sb.reverse().toString());

                String newStr = str.repeat(k);
                for (int j = 0; j < newStr.length(); ++j) {
                    stack.push(newStr.charAt(j));
                }
            }
        }

        // Convert stack to a string
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.reverse().toString();
    }
}
