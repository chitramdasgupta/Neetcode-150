// Time: O (max (queriesLen * longestQuery, wordsLen * longestWord))
// Space: O (max (queriesLen, wordsLen))
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] wordsFreq = calculateSmallestFrequency(words);
        int[] queriesFreq = calculateSmallestFrequency(queries);

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int queryFreq = queriesFreq[i];
            for (int wordVal : wordsFreq) {
                if (wordVal > queryFreq) {
                    res[i] += 1;
                }
            }
        }

        return res;
    }

    private int[] calculateSmallestFrequency(String[] strings) {
        int[] stringFreq = new int[strings.length];
        for (int i = 0; i < strings.length; ++i) {
            String str = strings[i];
            char smallestChar = str.charAt(0);
            for (int j = 1; j < str.length(); ++j) {
                smallestChar = ((int) str.charAt(j)) < ((int) smallestChar) ? str.charAt(j) : smallestChar;
            }

            int freq = 0;
            for (int j = 0; j < str.length(); ++j) {
                if (str.charAt(j) == smallestChar) {
                    ++freq;
                }
            }

            stringFreq[i] = freq;
        }

        return stringFreq;
    }
}
