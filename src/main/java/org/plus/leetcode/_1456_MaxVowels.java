package org.plus.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 * 1456. 定长子串中元音的最大数目
 * 给你字符串 s 和整数 k 。
 *
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 *
 * 英文中的 元音字母 为（a, e, i, o, u）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abciiidef", k = 3
 * 输出：3
 * 解释：子字符串 "iii" 包含 3 个元音字母。
 * 示例 2：
 *
 * 输入：s = "aeiou", k = 2
 * 输出：2
 * 解释：任意长度为 2 的子字符串都包含 2 个元音字母。
 * 示例 3：
 *
 * 输入：s = "leetcode", k = 3
 * 输出：2
 * 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
 * 示例 4：
 *
 * 输入：s = "rhythms", k = 4
 * 输出：0
 * 解释：字符串 s 中不含任何元音字母。
 * 示例 5：
 *
 * 输入：s = "tryhard", k = 4
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 * 1 <= k <= s.length
 */
public class  _1456_MaxVowels {

    public static void main(String[] args) {
        _1456_MaxVowels vowels = new _1456_MaxVowels();
        System.out.println(vowels.maxVowels("abciiidef", 3)); // 3
        System.out.println(vowels.maxVowels("aeiou", 2)); // 2
        System.out.println(vowels.maxVowels("leetcode", 3)); // 2
        System.out.println(vowels.maxVowels("rhythms", 4)); // 0
        System.out.println(vowels.maxVowels("tryhard", 4)); // 1
    }

    private static final String VOWELS = "aeiou";

    public int maxVowels(String s, int k) {
        String[] arr = s.split("");
        int sum = Arrays.stream(arr).limit(k).filter(VOWELS::contains).mapToInt(w -> 1).sum();
        if (s.length() == k) {
            return sum;
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i + k > s.length()) break;
            int li = i;
            int ri = i + k - 1;
            if (sum > max) max = sum;
            final String v = arr[li];
            if (VOWELS.contains(v)) {
                sum -= 1;
            }
            if (ri + 1 < arr.length) {
                final String rv = arr[ri + 1];
                if (VOWELS.contains(rv)) {
                    sum += 1;
                }
            }
        }
        return max;
    }

    /**
     * 使用双指针维护长度为k的窗口
     * https://leetcode-cn.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/solution/javashuang-zhi-zhen-by-alex-3b/
     * @param s
     * @param k
     * @return
     */
    public int maxVowels1(String s, int k) {
        int n = s.length();
        int count = 0, result = 0;
        int i = 0;

        for (int j = 0; j < n; j++) {
            if ("aeiou".indexOf(String.valueOf(s.charAt(j))) != -1) count++;
            if (j > k - 1) {
                if ("aeiou".indexOf(String.valueOf(s.charAt(i))) != -1) count--;
                i++;
            }
            result = Math.max(result, count);
        }

        return result;
    }

    /**
     * 别人都这么牛逼，你还有什么不努力？哎...心都碎了一地了...
     *
     * 总结：
     * 1.分析问题，找到问题的关键所在。
     * 2.理清思路再写代码，要多优化代码。
     * 3.善于总结和反思自身的不足。
     */
}
