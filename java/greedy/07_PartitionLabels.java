class Solution {
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            lastIndex.put(s.charAt(i), i);
        }

        List<Integer> res = new ArrayList<>();
        int size = 0;
        int end = 0;
        for (int i = 0; i < s.length(); ++i) {
            size += 1;
            end = Math.max(lastIndex.get(s.charAt(i)), end);

            if (i == end) {
                res.add(size);
                size = 0;
            }
        }

        return res;
    }
}
