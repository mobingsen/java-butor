package org.example.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mbs on 2021-06-24 18:04
 */
public class Node {
    private final Map<Integer, Node> map = new HashMap<>(26);
    private boolean end;

    public Node() {
    }

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
