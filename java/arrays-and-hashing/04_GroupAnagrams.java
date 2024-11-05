class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] freq = new int[26];
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                freq[c - 'a'] += 1;
            }

            String key = Arrays.toString(freq);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }
}
