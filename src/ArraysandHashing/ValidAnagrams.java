package ArraysandHashing;

import java.util.HashMap;

public class ValidAnagrams {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> freq = new HashMap<>();
        for(int i=0; i<s.length(); ++i) {
            if(freq.containsKey(s.charAt(i))) {
                freq.put(s.charAt(i), freq.get(s.charAt(i)) + 1);
            } else {
                freq.put(s.charAt(i), 1);
            }
        }

        for(int i=0; i<t.length(); ++i) {
            if (!freq.containsKey(t.charAt(i))) {
                return false;
            } else if (freq.get(t.charAt(i)) == 0) {
                return false;
            } else {
                freq.put(t.charAt(i), freq.get(t.charAt(i)) - 1);
            }
        }

        return true;
    }
}
