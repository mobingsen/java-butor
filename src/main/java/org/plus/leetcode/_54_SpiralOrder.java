package org.plus.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/spiral-matrix/
 * @author by mbs on 2021/3/16 11:15
 */
public class _54_SpiralOrder {

    public static void main(String[] args) {
        _54_SpiralOrder spiralOrder = new _54_SpiralOrder();
        List<Integer> list = spiralOrder.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println(list);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int acr = 0;
        int[][] grip = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                grip[i][j] = acr++;
                System.out.print(grip[i][j] + "\t");
            }
            System.out.println();
        }
        return result;
    }
}
