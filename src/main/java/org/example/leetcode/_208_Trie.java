package org.example.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/submissions/
 * Created by kidd on 2021/1/6 21:59
 */
public class _208_Trie {

    private final Node root = new Node();

    public static void main(String[] args) {
        _208_Trie trie = new _208_Trie();
        trie.insert("apple");
        // 返回 true
        System.out.println(trie.search("apple"));
        // 返回 false
        System.out.println(trie.search("app"));
        // 返回 true
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        // 返回 true
        System.out.println(trie.search("app"));
    }

    public _208_Trie() {
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            node = node.computeIfAbsent(c);
        }
        node.setEnd();
    }
    // search a prefix or whole key in trie and
    // returns the node where search ends
    private Node find(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            node = node.getOrNull(c);
            if (node == null) {
                break;
            }
        }
        return node;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        Node node = find(word);
        return node != null && node.isEnd();
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }
}

class Node {
    private final Map<Integer, Node> map = new HashMap<>(26);
    private boolean end;
    public Node() {}
    public Node getOrNull(char ch) {
        return map.get(ch - 'a');
    }
    public Node computeIfAbsent(char ch) {
        return map.computeIfAbsent(ch - 'a', v -> new Node());
    }
    public void setEnd() {
        end = true;
    }
    public boolean isEnd() {
        return end;
    }
}