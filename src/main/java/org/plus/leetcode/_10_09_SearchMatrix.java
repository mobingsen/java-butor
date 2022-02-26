package org.plus.leetcode;

/**
 * https://leetcode-cn.com/problems/sorted-matrix-search-lcci/
 * @author mbs on 2021-07-08 11:46
 */
public class _10_09_SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        final int w = matrix[0].length;
        int h = matrix.length;
        for (int i = h - 1; i >= 0; i--) {
            if (matrix[i][w -1] < target) {
                break;
            }
            for (int j = 0; j < w; j++) {
                if (matrix[i][j] == target) {
                    return true;
                } else if (matrix[i][j] > target) {
                    break;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final _10_09_SearchMatrix matrix = new _10_09_SearchMatrix();
        final int[][] ints = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(matrix.searchMatrix(ints, 5));
        System.out.println(matrix.searchMatrix(ints, 20));
    }
}
