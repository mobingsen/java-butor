package org.plus;

/**
 * 乱序执行
 * @author mbs on 2021-06-16 9:04
 */
public class _44_Disorder {

    private static int x;
    private static int y;
    private static int a;
    private static int b;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            final Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });
            final Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        }
    }
}
