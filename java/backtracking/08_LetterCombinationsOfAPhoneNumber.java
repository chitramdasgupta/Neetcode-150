class Solution {
    private List<String> res = new ArrayList<>();
    private Map<Character, String> digitsMap = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return res;
        }

        digitsMap.put('2', "abc");
        digitsMap.put('3', "def");
        digitsMap.put('4', "ghi");
        digitsMap.put('5', "jkl");
        digitsMap.put('6', "mno");
        digitsMap.put('7', "pqrs");
        digitsMap.put('8', "tuv");
        digitsMap.put('9', "wxyz");

        backtrack(digits, new StringBuilder(), 0);
        return res;
    }

    private void backtrack(String digits, StringBuilder curr, int start) {
        if (start == digits.length()) {
            res.add(curr.toString());
            return;
        }

        String values = digitsMap.get(digits.charAt(start));
        for (int i = 0; i < values.length(); ++i) {
            curr.append(values.charAt(i));
            backtrack(digits, curr, start + 1);
            curr.deleteCharAt(curr.length() - 1);
        }   
    }
}
