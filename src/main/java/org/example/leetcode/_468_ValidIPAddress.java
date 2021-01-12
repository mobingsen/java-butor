package org.example.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/validate-ip-address/
 * Created by modou on 2021/1/12 22:09
 */
public class _468_ValidIPAddress {

    public static void main(String[] args) {
        _468_ValidIPAddress address = new _468_ValidIPAddress();
        System.out.println(address.validIPAddress("172.16.254.1")); // "IPv4"
        System.out.println(address.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334")); // "IPv6"
        System.out.println(address.validIPAddress("256.256.256.256")); // "Neither"
        System.out.println(address.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:")); // "Neither"
        System.out.println(address.validIPAddress("1e1.4.5.6")); // "Neither"
        System.out.println(address.validIPAddress("2001:0db8:85a3:00000:0:8A2E:0370:7334")); // "Neither"
        System.out.println(address.validIPAddress("2001:0db8:85a3:0000:0:8A2E:0370:733a")); // "IPv6"
    }

    public String validIPAddress(String IP) {
        int sum = Arrays.stream(IP.split("")).mapToInt(e -> ".".equals(e) ? 1 : 0).sum();
        String[] split = IP.split("\\.");
        if (split.length == 4 && sum == 3) {
            boolean match = Arrays.stream(split).anyMatch(chip -> chip.isEmpty() ||
                    chip.startsWith("0") || has(chip) || Integer.parseInt(chip) > 255);
            return match ? Neither : IPv4;
        }
        split = IP.split(":");
        sum = Arrays.stream(IP.split("")).mapToInt(e -> ":".equals(e) ? 1 : 0).sum();
        if (split.length == 8 && sum == 7) {
            Function<String, Boolean> zero = w -> {
                Map<String, List<String>> map = Arrays.stream(w.split(""))
                        .collect(Collectors.groupingBy(Function.identity()));
                List<String> list = map.get("0");
                if (map.size() > 1 || map.size() == 1 && list == null || map.size() == 1 && list.size() == 1) {
                    return false;
                }
                return Arrays.stream(w.split("")).allMatch("0"::equals);
            };
            Function<String, Boolean> has = w -> {
                for (char c : w.toCharArray()) {
                    return (c > 'f') || (c > 'F' && c < 'a');
                }
                return false;
            };
            boolean match = Arrays.stream(split).anyMatch(chip -> chip.isEmpty() || chip.length() > 4 || zero.apply(chip) || has.apply(chip));
            return match ? Neither : IPv6;
        }
        return Neither;
    }

    private boolean has(String word) {
        for (char c : word.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                return true;
            }
        }
        return false;
    }

    private static final String Neither = "Neither";
    private static final String IPv6 = "IPv6";
    private static final String IPv4 = "IPv4";
}
