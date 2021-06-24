package org.example.leetcode;

/**
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class _543_DiameterOfBinaryTree {

    private int m = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        trace(root);
        return m - 1;
    }

    private int trace(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int l = trace(node.left);
        int r = trace(node.right);
        m = Math.max(m, l + r + 1);
        return Math.max(l, r) + 1;
    }

    public static void main(String[] args) {
        final TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));
        final _543_DiameterOfBinaryTree solution = new _543_DiameterOfBinaryTree();
        final int i = solution.diameterOfBinaryTree(root);
        System.out.println(i);
    }
}

