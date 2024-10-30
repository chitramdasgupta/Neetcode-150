class Trie {
    class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean isWord;
    }

    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (curr.children.get(c) == null) {
                curr.children.put(c, new Node());
            }

            curr = curr.children.get(c);
        }

        curr.isWord = true;
    }

    public boolean search(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (curr.children.get(c) == null) {
                return false;
            }

            curr = curr.children.get(c);
        }

        return curr.isWord;
    }

    public boolean startsWith(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); ++i) {
            char c = prefix.charAt(i);
            if (curr.children.get(c) == null) {
                return false;
            }

            curr = curr.children.get(c);
        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
