class WordDictionary {
    class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean isWord;
    }

    private final Node root;

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);

            if(curr.children.get(c) == null) {
                curr.children.put(c, new Node());
            }

            curr = curr.children.get(c);
        }

        curr.isWord = true;
    }

    public boolean search(String word) {
        return backtrack(word, 0, root);
    }

    private boolean backtrack(String word, int index, Node curr) {
        if (index == word.length()) {
            return curr.isWord;
        }

        char c = word.charAt(index);
        if (c == '.') {
            for (char ch = 'a'; ch <= 'z'; ++ch) {
                Node next = curr.children.get(ch);
                if (next != null && backtrack(word, index + 1, next)) {
                    return true;
                }
            }
        } else {
            Node next = curr.children.get(c);
            if (next != null) {
                return backtrack(word, index + 1, next);
            }   
        }

        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
