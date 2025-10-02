public class Trie {

    // Inner class representing each node in the Trie
    class TrieNode {
        TrieNode[] children;  // array of child references (a-z)
        boolean isEndOfWord;  // true if this node marks the end of a word

        public TrieNode() {
            children = new TrieNode[26]; // English lowercase letters
            isEndOfWord = false;
        }
    }

    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    // Search for a full word
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEndOfWord;
    }

    // Check if any word starts with a given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return true;
    }

    // Print all words stored in the Trie
    public void printAll() {
        printAllHelper(root, new StringBuilder());
    }

    // Recursive DFS helper
    private void printAllHelper(TrieNode node, StringBuilder prefix) {
        if (node.isEndOfWord) {
            System.out.println(prefix.toString());
        }
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                prefix.append((char) ('a' + i));  // add current char
                printAllHelper(node.children[i], prefix);
                prefix.deleteCharAt(prefix.length() - 1); // backtrack
            }
        }
    }
}
