package leetcode.trie;

public class PrefixTree {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");   // return True
        trie.search("app");     // return False
        trie.startsWith("app"); // return True // fix this
        trie.insert("app");
        trie.search("app");     // return True
    }
}

class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        var curr = root;

        for (int i = 0; i < word.length(); i++) {
            var ch = word.charAt(i);

            if (curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        var curr = root;

        for (int i = 0; i < word.length(); i++) {
            var ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null) {
                return false;
            }
            curr = curr.children[ch - 'a'];
        }
        return curr.isEnd;
    }

    public boolean startsWith(String prefix) {
        var curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            var ch = prefix.charAt(i);
            if (curr.children[ch - 'a'] == null) {
                return false;
            }
            curr = curr.children[ch - 'a'];
        }
        return true;
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
}