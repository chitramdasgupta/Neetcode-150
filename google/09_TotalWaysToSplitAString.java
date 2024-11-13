class Solution {
    public int totalWaysToSplitStrings(String s) {
        int len = s.length();
        Set<Character> seen = new HashSet<>();

        int[] left = new int[len]; // No. of unique characters from start through index
        for (int i = 0; i < len; i++) {
            seen.add(s.charAt(i));
            left[i] = seen.size();
        }

        seen.clear();

        int[] right = new int[len]; // No. of unique characters from index through the last character
        for (int i = len - 1; i >= 0; --i) {
            seen.add(s.charAt(i));
            right[i] = seen.size();
        }

        int res = 0;
        for (int i = len - 2; i >= 0; --i) {
            if (left[i] == right[i + 1]) {
                ++res;
            }
        }

        return res;
    }
}
