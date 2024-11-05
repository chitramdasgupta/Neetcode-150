class Solution {
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            int len = s.length();
            sb.append(len);
            sb.append('#');
            sb.append(s);
        }

        return sb.toString();
    }

    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            // Find the delimiter '#'
            int j = i;
            while (j < str.length() && str.charAt(j) != '#') {
                j++;
            }

            // Parse the length
            int len = Integer.parseInt(str.substring(i, j));

            // Extract the string
            String s = str.substring(j + 1, j + 1 + len);
            res.add(s);

            // Move to the start of next length indicator
            i = j + 1 + len;
        }

        return res;
    }
}
