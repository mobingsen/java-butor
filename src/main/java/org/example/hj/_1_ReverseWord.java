package org.example.hj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by mobingsen on 2020/10/17 16:07
 */
public class _1_ReverseWord {

    /**
     * ready go
     * ydaer og
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null && line.replaceAll(" ", "").length() > 0) {
            String[] split = line.split(" ");
            String result = Arrays.stream(split)
                    .map(StringBuilder::new)
                    .map(StringBuilder::reverse)
                    .collect(Collectors.joining(" "));
            System.out.println(result);
        }
    }
}
