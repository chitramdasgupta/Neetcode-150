1. Implement Trie (Prefix Tree)

```cpp
class TrieNode {
 public:
  array<TrieNode*, 26> children;
  bool isWord;

  TrieNode() {
    children.fill(nullptr);
    isWord = false;
  }
};

class Trie {
 public:
  Trie() {
    root = new TrieNode();
  }

  void insert(string word) {
    TrieNode* node = root;

    for (char c : word) {
      int pos = c - 'a';
      if (node->children[pos] == nullptr) {
        node->children[pos] = new TrieNode();
      }

      node = node->children[pos];
    }

    node->isWord = true;
  }

  bool search(string word) {
    TrieNode* node = root;

    for (char c : word) {
      int pos = c - 'a';
      if (node->children[pos] == nullptr) {
        return false;
      }

      node = node->children[pos];
    }

    return node->isWord;
  }

  bool startsWith(string prefix) {
    TrieNode* node = root;

    for (char c : prefix) {
      int pos = c - 'a';
      if (node->children[pos] == nullptr) {
        return false;
      }

      node = node->children[pos];
    }

    return true;
  }

 private:
  TrieNode* root;
};
```

2. Design Add and Search Words Data Structure

```cpp
class TrieNode {
public:
  array<TrieNode *, 26> children;
  bool isWord;

  TrieNode() {
    children.fill(nullptr);
    isWord = false;
  }
};

class WordDictionary {
public:
  WordDictionary() {
    root = new TrieNode();
  }

  void addWord(string word) {
    TrieNode *node = root;

    for (char c : word) {
      int pos = c - 'a';
      if (node->children[pos] == nullptr) {
        node->children[pos] = new TrieNode();
      }

      node = node->children[pos];
    }

    node->isWord = true;
  }

  bool search(string word) {
    return dfs(word, root, 0);
  }

private:
  TrieNode *root;

  bool dfs(string &word, TrieNode *node, int idx) {
    if (idx == word.size()) {
      return node->isWord;
    }

    if (word[idx] != '.') {
      int pos = word[idx] - 'a';
      if (node->children[pos] == nullptr) {
        return false;
      }

      return dfs(word, node->children[pos], idx + 1);
    }

    for (TrieNode *child : node->children) {
      if (child && dfs(word, child, idx + 1)) {
        return true;
      }
    }

    return false;
  }
};
```
