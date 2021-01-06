package org.example.leetcode;

import org.example.utils.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/replace-words/
 * Created by mbs on 2021/1/5 8:56
 */
public class _648_ReplaceWords {

    public static void main(String[] args) {
        final _648_ReplaceWords replaceWords = new _648_ReplaceWords();
        final String result1 = replaceWords.replaceWords(
                Arrays.asList("cat", "bat", "rat"), "the cattle was rattled by the battery");
        Assert.isTrue("the cat was rat by the bat".equals(result1));
        final String result2 = replaceWords.replaceWords(
                Arrays.asList("a","b","c"), "aadsfasf absbs bbab cadsfafs");
        Assert.isTrue("a a b c".equals(result2));
        final String result3 = replaceWords.replaceWords(
                Arrays.asList("a", "aa", "aaa", "aaaa"), "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa");
        Assert.isTrue("a a a a a a a a bbb baba a".equals(result3));
        final String result4 = replaceWords.replaceWords(
                Arrays.asList("catt","cat","bat","rat"), "the cattle was rattled by the battery");
        Assert.isTrue("the cat was rat by the bat".equals(result4));
        final String result5 = replaceWords.replaceWords(
                Arrays.asList("ac","ab"), "it is abnormal that this solution is accepted");
        Assert.isTrue("it is ab that this solution is ac".equals(result5));
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        return null;
    }
}
