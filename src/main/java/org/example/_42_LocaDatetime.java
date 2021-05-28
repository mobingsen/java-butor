package org.example;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author mbs on 2021/5/28 9:31
 */
public class _42_LocaDatetime {

    public static void main(String[] args) {
        final Instant instant = LocalDateTime
                .parse("2021-05-28 14:41:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(instant.toString());
    }
}
