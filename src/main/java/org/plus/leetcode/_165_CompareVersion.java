package org.plus.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/compare-version-numbers/
 * @author by mobingsen on 2021/9/1 16:43
 */
public class _165_CompareVersion {

    public int compareVersion(String version1, String version2) {
        int ret = 0;
        Integer[] v1 = handleVersion(version1);
        Integer[] v2 = handleVersion(version2);
        int len = Math.max(v1.length, v2.length);
        for (int i = 0; i < len; i++) {
            int e1 = i < v1.length ? v1[i] : 0;
            int e2 = i < v2.length ? v2[i] : 0;
            if (e1 > e2) {
                ret = 1;
                break;
            } else if (e1 < e2) {
                ret = -1;
                break;
            }
        }
        return ret;
    }

    private Integer[] handleVersion(String v) {
        return Arrays.stream(v.split("\\.")).map(Integer::parseInt).toArray(Integer[]::new);
    }

    public static void main(String[] args) {
        _165_CompareVersion obj = new _165_CompareVersion();
        System.out.println(obj.compareVersion("1.01", "1.001"));
        System.out.println(obj.compareVersion("1.0", "1.000"));
        System.out.println(obj.compareVersion("0.1", "1.1"));
        System.out.println(obj.compareVersion("1.0.1", "1"));
        System.out.println(obj.compareVersion("7.5.2.4", "7.5.3"));
        System.out.println(obj.compareVersion("1.0", "1.0.0"));
    }
}
