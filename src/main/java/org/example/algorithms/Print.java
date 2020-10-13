package org.example.algorithms;

/**
 * Created by 小墨 on 2020/7/2 15:33
 */
public class Print {

    public static void write(String desc, int[] arr) {
        System.out.println(desc);
        for (int value : arr) {
            System.out.print("\t" + value);
        }
    }
}
