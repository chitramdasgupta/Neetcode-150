package Trie;

public class DesignAddSearchWordsDataStruct {
    TrieNode root;

    public DesignAddSearchWordsDataStruct() {
        root = new TrieNode();
    }

    // Time - O(n)
    // Space - O(n)
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); ++i) {
            int idx = word.charAt(i) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isWord = true;
    }

    // Time - O(m * 26^n)
    // Space - O(n) where m is the number of words, n is the length of the words
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    boolean dfs(String word, int idx, TrieNode node) {
        if (node == null) {
            return false;
        }
        for (int i = idx; i < word.length(); ++i) {
            if (word.charAt(i) == '.') {
                for (TrieNode child : node.children) {
                    if (dfs(word, i + 1, child)) {
                        return true;
                    }
                }
                return false;
            } else {
                int j = word.charAt(i) - 'a';
                if (node.children[j] == null) {
                    return false;
                }
                node = node.children[j];
            }
        }

        return node.isWord;
    }

    private class TrieNode {
        TrieNode[] children;
        boolean isWord;

        TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }
}
