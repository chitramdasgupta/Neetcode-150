class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        Map<Character, Integer> firstFreq = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            char c = s1.charAt(i);
            firstFreq.put(c, firstFreq.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> secondFreq = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s2.charAt(i);
            secondFreq.put(c, secondFreq.getOrDefault(c, 0) + 1);
        }

        if (firstFreq.equals(secondFreq)) {
            return true;
        }

        for (int i = s1.length(); i < s2.length(); i++) {
            // Add new character
            char c = s2.charAt(i);
            secondFreq.put(c, secondFreq.getOrDefault(c, 0) + 1);

            // Remove leftmost character
            char leftChar = s2.charAt(i - s1.length());
            secondFreq.put(leftChar, secondFreq.getOrDefault(leftChar, 0) - 1);
            if (secondFreq.get(leftChar) == 0) {
                secondFreq.remove(leftChar);
            }

            if (firstFreq.equals(secondFreq)) {
                return true;
            }
        }

        return false;
    }
}

