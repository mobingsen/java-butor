package org.example.leetcode;

/**
 * https://leetcode-cn.com/problems/number-of-matching-subsequences/submissions/
 * @Created by mbs on 2020/12/31 11:41
 */
public class _792_NumMatchingSubseq {

    public static void main(String[] args) {
        _792_NumMatchingSubseq matchingSubseq = new _792_NumMatchingSubseq();
        // S = "abcde"
        //words = ["a", "bb", "acd", "ace"]
//        int num = matchingSubseq.numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"});
//        System.out.println(num);
        // "dsahjpjauf"
        //["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
        int num2 = matchingSubseq.numMatchingSubseq("dsahjpjauf", new String[]{"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"});
        System.out.println(num2);
    }

    public int numMatchingSubseq(String S, String[] words) {
        if (S == null || S.length() < 1) {
            return 0;
        }
        int num = 0;
        for (String word : words) {
            int index = 0;
            int num2 = 0;
            for (String w : word.split("")) {
                String[] split = S.split("");
                for (int i = index; i < split.length; i++) {
                    if (w.equals(split[i])) {
                        index = i + 1;
                        num2++;
                        break;
                    }
                }
            }
            if (num2 == word.length()) {
                num++;
            }
        }
        return num;
    }
}
