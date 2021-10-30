package org.example.algorithms;

/**
 * 冒泡排序
 * 基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。
 * 即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
 * Created by mobingsen on 2020/7/6 20:35
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1, 8 };
        Print.write("排序前", a);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        System.out.println();
        Print.write("排序后", a);
    }

    /**
     * 交换arr的i和j位置上的值
     *
     * 异或运算，不同为1，相同为0.比如0^1=1,1^0=1,1^1=0,0^0=0,还可以理解为无进位相加，比如10110^00111=10001
     * 异或性质：
     *  1.0^N=N， N^N=0，比如：a^b=b^a, a^b^c=a^(b^c)
     *  2.满足交换律、结合律
     *  3.
     * @param arr 待排序的数据
     * @param i   i
     * @param j   j
     */
    public static void swap(int[] arr, int i, int j) {
        /*
            int a = 甲
            int b = 乙
            a = a ^ b; => a = 甲 ^ 乙， b = 乙
            b = a ^ b; => a = 甲 ^ 乙， b = 甲 ^ 乙 ^ 乙 = 甲 ^ (乙 ^ 乙 = 0) = 甲 ^ 0 = 甲
            a = a ^ b; => a = 甲 ^ 乙 ^ 甲 = (甲 ^ 甲 = 0) ^ 乙 = 0 ^ 乙 = 乙， b = 甲
            这么写还有一个问题就是 a和b的内存地址必须是不一样的，即是说i位置上的值不能和j位置上的值是一样的，因为相同的话会产生覆盖的情况
         */
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 面试题：
     * 在一个数组中
     * 1.只有一种数出现了奇数次，其他的数出现了偶数次，怎么找到出现奇数次的数？
     *  比如arr = [a, b, c...], 定义个标量eor，让eor = eor ^ arr[i]，最后按个数就是所求的结果了
     * 2.在数组中已知两种数出现了奇数次，其他的都出现了偶数次，怎么找到出现奇数次的那两种数？
     *  最终eor = a ^ b, a 和 b 中肯定有某个位上是0或者1，故在用一个变量eor·再去^一遍就得到了a或者b了
     *
     * 要求：时间复杂度：o（n），空间复杂度：o（1）
     */
    public static void interview(int[] arr) {
        // 1
        interview1(arr);
        // 2
        interview2(arr);
    }

    private static void interview2(int[] arr) {
        int eor = 0;
        int onlyOne = 0;
        for (int curNum : arr) {
            eor ^= curNum;
        }
        // eor = a ^ b
        // eor != 0 因为有两种数不同
        // eor必然有一个位置上是1

        // 提取出最右的1,即是找出最右则的不同的位1找出来
        int rightOne = eor & (~eor + 1);

        for (int cur : arr) {
            // 可以=0或者=1
            if ((cur & rightOne) == 0) {
                onlyOne ^= cur;
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    public static void interview1(int[] arr) {
        // 1
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        System.out.println(eor);
    }
}
