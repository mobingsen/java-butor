package org.plus.algorithms.code06;

/**
 * @author mobingsen
 * @date 2021/11/21 8:49
 */
public class Edge {

    public Node from;
    public Node to;
    public int weight;

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
