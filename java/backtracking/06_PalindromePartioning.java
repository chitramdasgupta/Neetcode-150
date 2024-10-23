class Solution {
    private List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, new ArrayList<>(), 0);
        return res;
    }

    private void backtrack(String s, List<String> curr, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(curr));
        }

        for (int i = start; i < s.length(); ++i) {
            String temp = s.substring(start, i + 1);

            if (isPalindrome(temp)) {
                curr.add(temp);
                backtrack(s, curr, i + 1);
                curr.removeLast();
            }
        }
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }

            ++left;
            --right;
        }

        return true;
    }
}
