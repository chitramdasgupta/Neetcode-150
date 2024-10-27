class Solution {
    public int numDecodings(String s) {
        int two = 1;
        int one = s.charAt(0) != '0' ? 1 : 0;

        for (int i = 1; i < s.length(); ++i) {
            int temp = 0;
            // We can take the current character by itself
            if (s.charAt(i) != '0') {
                temp += one;
            }

            if (i > 0 && s.charAt(i - 1) != '0' &&
                Integer.parseInt(s.substring(i - 1, i + 1)) >= 0 &&
                Integer.parseInt(s.substring(i - 1, i + 1)) <= 26) {
                // We can take the current character and the previous character
                temp += two;
            }

            two = one;
            one = temp;
        }

        return one;
    }
}
