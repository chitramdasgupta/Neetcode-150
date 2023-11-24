package ArraysandHashing;

import java.util.ArrayList;
import java.util.List;

// Time - O(n)
// Space - O(1)
public class EncodeAndDecodingStrings {
    public String encode(List<String> strs) {
        StringBuilder res = new StringBuilder();

        for(String str : strs) {
            int len = str.length();
            res.append(len);
            res.append("#");
            res.append(str);
        }

        return res.toString();
    }

    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while(i < str.length()) {
            int j = i;
            while(str.charAt(j) != '#') {
                j += 1;
            }
            int len = Integer.parseInt(str.substring(i, j));
            i = j + 1 + len;
            res.add(str.substring(j + 1, i));
        }

        return res;
    }
}
