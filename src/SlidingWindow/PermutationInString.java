package SlidingWindow;

import java.util.Arrays;

// Time - O(n)
// Space - O(1)
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] firstFreq = new int[26];
        for (int i = 0; i < s1.length(); ++i) {
            int idx = s1.charAt(i) - 'a';
            firstFreq[idx] += 1;
        }

        int[] secondFreq = new int[26];
        // We first fill up the frequency of the characters in s2 till we reach
        // length of s1 (Since a permutation of s1 has to be of the same length
        // s1 itself). Then we slide the window (of size s1.length()) by 1 to
        // the right one by one and update the frequency of the chars and
        // compare the frequencies.
        for (int i = 0; i < s2.length(); ++i) {
            int idx = s2.charAt(i) - 'a';
            secondFreq[idx] += 1;
            if (i >= s1.length()) {
                int newIdx = s2.charAt(i - s1.length()) - 'a';
                secondFreq[newIdx] -= 1;
            }
            if (Arrays.equals(firstFreq, secondFreq)) {
                return true;
            }
        }

        return false;
    }
}
