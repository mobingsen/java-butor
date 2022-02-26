package org.plus.leetcode;

/**
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 */
public class _240_SearchMatrix {

    public static void main(String[] args) {
        _240_SearchMatrix matrix = new _240_SearchMatrix();
        System.out.println(matrix.searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5));
        System.out.println(matrix.searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int height = matrix.length - 1, width = 0;
        while (height >= 0 && width < matrix[0].length) {
            if (matrix[height][width] > target) {
                height--;
            } else if (matrix[height][width] < target) {
                width++;
            } else {
                return true;
            }
        }
        return false;
    }
}
