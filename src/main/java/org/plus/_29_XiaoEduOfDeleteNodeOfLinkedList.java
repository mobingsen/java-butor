package org.plus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 请使用java或java伪代码实现以下需求：给定一个单向链表如1->2->3->...->n,链表长度未知，请删除第n/2个节点。如1->3->4->7->9->12,删除
 * 第3（6/2）节点后变为1->3->7->9->12。要求不能使用java集合类，请使用自定义数据结构表示链表。请注意，你不能假定链表长度已知。
 */
public class _29_XiaoEduOfDeleteNodeOfLinkedList {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Node f = null;
        Node p = null;
        while ((line = br.readLine()) != null && line.length() > 0) {
            Node n = new Node(Integer.parseInt(line), p, null);
            if (f == null) {
                f = n;
            }
            if (p != null) {
                p.setN(n);
            }
            p = n;
        }
        int len = 1;
        Node t = f;
        while ((t = t.n) != null) {
            len++;
        }
        int m = len / 2;
        t = f;
        len = 1;
        while ((t = t.n) != null) {
            len++;
            if (len == m) {
                t.p.n = t.n;
                t.n.p = t.p.n;
            }
        }
        t = f;
        StringBuilder sb = new StringBuilder(f.v);
        while ((t = t.n) != null) {
            sb.append(t.v).append(" ");
        }
        System.out.println(sb.toString());
        // 这种做法很惯性思维。还有更加方便的做法吗???????????????????????????????
        t = f;
        Node s = f;
        while ((t = t.n) != null) {
            if ((s = s.n.n) == null) {
                t.p.n = t.n;
                t.n.p = t.p.n;
                break;
            }
        }
        // 上面的做法就是通过快慢指针的方法来实现的。
    }

    private static class Node {
        Integer v;
        Node p;
        Node n;

        public Node(Integer v, Node p, Node n) {
            this.v = v;
            this.p = p;
            this.n = n;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public Node getP() {
            return p;
        }

        public void setP(Node p) {
            this.p = p;
        }

        public Node getN() {
            return n;
        }

        public void setN(Node n) {
            this.n = n;
        }
    }
}
