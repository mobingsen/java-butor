package org.example.time;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by mobingsen on 2020/10/13 9:36
 */
public class YWDays {

    public static void main(String[] args) {
        long days = ChronoUnit.DAYS.between(
                LocalDate.of(2017, 7, 24),
                LocalDate.of(2020, 10, 13)
        );
        System.out.println(days);
    }
}
