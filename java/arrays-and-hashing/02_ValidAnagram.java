class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> firstFreq = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            firstFreq.put(s.charAt(i), firstFreq.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < t.length(); ++i) {
            if (!firstFreq.containsKey(t.charAt(i))) {
                return false;
            }

            firstFreq.put(t.charAt(i), firstFreq.getOrDefault(t.charAt(i), 0) - 1);
            if (firstFreq.get(t.charAt(i)) < 0) {
                return false;
            }
        }

        return true;
    }
}
