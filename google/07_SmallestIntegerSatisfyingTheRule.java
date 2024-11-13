class Solution {
    public String minPossibleInt(String digits) {
        int len = digits.length();
        char[] res = digits.toCharArray();
        for (int i = 0; i < len; i++) {
            if (res[i] != '?') {
                continue;
            }

            for (char c = '1'; c <= '9'; ++c) {
                if ((i > 0 && res[i -1] == c) || (i < len - 1 && digits.charAt(i + 1) == c)) {
                    continue;
                }

                res[i] = c;
                break;
            }
        }

        return new String(res);
    }
}
