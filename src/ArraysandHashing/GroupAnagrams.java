package ArraysandHashing;

import java.util.*;

// Assume there are N strings, and each string is of length K
// Time - O(N * K * log(K))
// Space - O(N * K)
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> anagrams = new HashMap<>();
        for(String s : strs) {
            char[] stringArray = s.toCharArray();
            Arrays.sort(stringArray);
            String sortedWord = Arrays.toString(stringArray);
            if (!anagrams.containsKey(sortedWord)) {
                anagrams.put(sortedWord, new ArrayList<>());
            }
            anagrams.get(sortedWord).add(s);
        }

        List<List<String>> ans = new ArrayList<>(anagrams.values());
        return ans;
    }
}
