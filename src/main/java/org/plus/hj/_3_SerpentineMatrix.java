package org.plus.hj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * https://www.nowcoder.com/practice/649b210ef44446e3b1cd1be6fa4cab5e?tpId=37&&tqId=21258&rp=1&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 *
 * 蛇形矩阵是由1开始的自然数依次排列成的一个矩阵上三角形。
 *
 * 例如，当输入5时，应该输出的三角形为：
 *
 * 1 3 6 10 15
 *
 * 2 5 9 14
 *
 * 4 8 13
 *
 * 7 12
 *
 * 11
 */
public class _3_SerpentineMatrix {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = br.readLine())!=null){
            int n = Integer.parseInt(str);
            int[][] arr = new int[n][n];
            int tem = 1;
            for (int i = 0; i < n; i++) {
                for (int j = i; j >= 0; j--) {
                    arr[j][i] = tem++;
                }
            }
            String result = Arrays.stream(arr).map(a -> Arrays.stream(a).filter(i -> i != 0)
                    .mapToObj(String::valueOf).collect(Collectors.joining(" ")))
                    .collect(Collectors.joining("\n"));
            System.out.println(result);
        }
    }
}
