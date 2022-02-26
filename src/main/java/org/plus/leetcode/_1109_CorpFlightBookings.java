package org.plus.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/corporate-flight-bookings/
 * @author by mobingsen on 2021/8/31 18:11
 */
public class _1109_CorpFlightBookings {

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] answers = new int[n];
        for (int[] booking : bookings) {
            int x = booking[0];
            int y = booking[1];
            int num = booking[2];
            for (int i = x; i <= y; i++) {
                int temp = answers[i - 1];
                answers[i - 1] = temp + num;
            }
        }
        return answers;
    }

    public static void main(String[] args) {
        _1109_CorpFlightBookings bookings = new _1109_CorpFlightBookings();
        int[][] b = new int[][] {{1,2,10}, {2,3,20}, {2,5,25}};
        System.out.println(Arrays.toString(bookings.corpFlightBookings(b, 5)));
    }
}
