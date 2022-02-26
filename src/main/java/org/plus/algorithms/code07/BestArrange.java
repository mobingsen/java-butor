package org.plus.algorithms.code07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法
 * @author mobingsen
 * @date 2021/11/19 23:10
 */
public class BestArrange {

    public static void main(String[] args) {
        int num = bestArrange(new Program[]{}, 6);
        System.err.println(num);
    }

    private static int bestArrange(Program[] programs, int timePoint) {
        Arrays.sort(programs, Comparator.comparingInt(Program::getEnd));
        int result = 0;
        // 从左往右遍历所有的会议
        for (Program program : programs) {
            if (timePoint <= program.getStart()) {
                result++;
                timePoint = program.getEnd();
            }
        }
        return result;
    }
}
