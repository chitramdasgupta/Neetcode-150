
class Solution {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();

        int highestFreq = 0;
        int left = 0;
        int res = 0;

        for (int right = 0; right < s.length(); ++right) {
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            highestFreq = Math.max(freq.get(c), highestFreq);

            int windowLength = right - left + 1;

            if (windowLength - highestFreq <= k) {
                res = Math.max(right - left + 1, res);
            }  else {
                freq.put(s.charAt(left), freq.get(s.charAt(left)) - 1);
                ++left;
            }
        }

        return res;
    }
}
