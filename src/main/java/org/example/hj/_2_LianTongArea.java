package org.example.hj;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 0 0 0 0 0 0 0 0 0 0
 * 0 1 0 0 0 0 0 0 0 0
 * 0 1 0 0 0 0 0 0 0 0
 * 0 1 1 1 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 1 1 1 1 1 1 1 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 1 0 0 0 0 0 0
 * 0 0 0 1 1 0 0 0 0 0
 * 0 0 0 1 1 1 1 1 0 0
 * 10*10,某个点的上下左右是1即可连通，对角线连通不算。
 * 5， 7， 8 => 最终结果输出 8
 * Created by 小墨 on 2020/10/18 13:11
 */
public class _2_LianTongArea {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null && line.replaceAll(" ", "").length() > 0) {
            List<Integer> list = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            int x = 1;
            int y = 1;
            List<Node> nodes = new ArrayList<>(100);
            for (Integer i : list) {
                Node node = Node.ctor(x++, y, i);
                if (x > 10) {
                    x = 1;
                    y++;
                }
                nodes.add(node);
            }
            Map<List<Node>, Integer> map = new HashMap<>();
            for (Node node : nodes) {
                List<Node> nodeList = map.keySet().stream().flatMap(Collection::stream).collect(Collectors.toList());
                if (node.isOne() && nodeList.stream().noneMatch(n -> n.getX() == node.getX() && n.getY() == node.getY())) {
                    List<Node> temps = new ArrayList<>();
                    temps.add(node);
                    int nodeX = node.getX();
                    int nodeY = node.getY();
                    find(nodes, nodeX, nodeY, temps, nodeList);
                    if (!temps.isEmpty()) {
                        map.put(temps, temps.size());
                    }
                }
            }
            System.out.println(map.entrySet().stream()
                    .map(e -> e.getKey().toString() + "===>" + e.getValue())
                    .collect(Collectors.joining("\n")));
            System.out.println(Collections.max(map.values()));
        }
    }

    private static void find(List<Node> nodes, int t, int p, List<Node> temps, List<Node> nodeList) {
        int t1 = t + 1;
        if (t1 <= 10) {
            doFind(nodes, t1, p, temps, nodeList);
        }
        int t2 = t - 1;
        if (t2 > 0) {
            doFind(nodes, t2, p, temps, nodeList);
        }
        int p1 = p + 1;
        if (p1 <= 10) {
            doFind(nodes, t, p1, temps, nodeList);
        }
        int p2 = p - 1;
        if (p2 > 0) {
            doFind(nodes, t, p2, temps, nodeList);
        }
    }

    private static void doFind(List<Node> nodes, int t, int p, List<Node> temps, List<Node> nodeList) {
        if (temps.stream().noneMatch(n -> n.getX() == t && n.getY() == p) &&
                nodeList.stream().noneMatch(n -> n.getX() == t && n.getY() == p)) {
            nodes.stream().filter(n -> n.getX() == t && n.getY() == p).findFirst().filter(Node::isOne)
                    .ifPresent(n -> {
                        temps.add(n);
                        find(nodes, n.getX(), n.getY(), temps, nodeList);
                    });
        }
    }

    @Getter
    @Setter
    static class Node {
        private int x;
        private int y;
        private int v;

        public boolean isOne() {
            return this.v == 1;
        }

        public String key() {
            return this.getX() + "_" + this.getY();
        }

        public static Node ctor(int x, int y, int v) {
            Node node = new Node();
            node.setX(x);
            node.setY(y);
            node.setV(v);
            return node;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.v * this.y);
        }

        @Override
        public boolean equals(Object obj) {
            if (null == obj) return false;
            if (!(obj instanceof Node)) return false;
            Node node = (Node) obj;
            return node.getX() == this.x && node.getY() == this.y;
        }

        @Override
        public String toString() {
            return this.x + "_" + this.y + ": " + this.v;
        }
    }
}
