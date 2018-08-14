// self defined Trie before reading LC solution.
// beats 2.61%
class Trie {
    Map<Character, Trie> children;
    boolean endOfPath; // used to mark if a node is the end of a inserted word.

    /** Initialize your data structure here. */
    public Trie() {
        children = new HashMap<>();
        endOfPath = false;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        int size = word.length();
        Trie root = this;
        for (int i = 0; i < size; i++) {
            char c = word.charAt(i);
            if (root.children.containsKey(c)) {
                root = root.children.get(c);
            } else {
                root.children.put(c, new Trie());
                root = root.children.get(c);
            }
        }
        root.endOfPath = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        int size = word.length();
        Trie root = this;
        for (int i = 0; i < size; i++) {
            char c = word.charAt(i);
            if (root.children.containsKey(c)) {
                root = root.children.get(c);
            } else {
                return false;
            }
        }
        //return root.children.isEmpty();
        return root.endOfPath;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        int size = prefix.length();
        Trie root = this;
        for (int i = 0; i < size; i++) {
            char c = prefix.charAt(i);
            if (root.children.containsKey(c)) {
                root = root.children.get(c);
            } else {
                return false;
            }
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