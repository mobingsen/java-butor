package org.plus.leetcode;

/**
 * https://leetcode-cn.com/problems/nim-game/submissions/
 * @Created by mobingsen on 2021/4/6 22:11
 */
public class _292_CanWinNim {


    public boolean canWinNim(int n) {
        if (n <= 3) {
            return true;
        }
        if (n == 4) {
            return false;
        }
        return n % 4 != 0;
    }
}
