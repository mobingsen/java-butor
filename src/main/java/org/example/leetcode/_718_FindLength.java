package org.example.leetcode;

/**
 * Created by mbs on 2020/12/14 18:27
 */
public class _718_FindLength {

    public static void main(String[] args) {
        final _718_FindLength o = new _718_FindLength();
        System.out.println(o.findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}));
    }

    public int findLength(int[] A, int[] B) {
        int m = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    int i2 = i;
                    int j2 = j;
                    int l = 1;
                    while (++i2 < A.length && ++j2 < B.length && A[i2] == B[j2]) {
                        if (++l > m) {
                            m = l;
                        }
                    }
                }
            }
        }
        return m;
    }
}
