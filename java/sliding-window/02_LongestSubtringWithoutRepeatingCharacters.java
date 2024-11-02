class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int left = 0;
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            freq.put(c, freq.getOrDefault(c, 0) + 1);

            while (freq.get(c) > 1) {
                freq.put(s.charAt(left), freq.getOrDefault(s.charAt(left), 0) - 1);
                ++left;
            }

            res = Math.max(i - left + 1, res);
        }

        return res;
    }
}
