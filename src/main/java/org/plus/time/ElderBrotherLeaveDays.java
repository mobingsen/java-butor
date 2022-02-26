package org.plus.time;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by mobingsen on 2020/10/13 09:30
 */
public class ElderBrotherLeaveDays {

    private final LocalDate leaveDay = LocalDate.of(2019, 5, 17);

    public long days() {
        return ChronoUnit.DAYS.between(leaveDay, LocalDate.now());
    }

    public static void main(String[] args) {
        System.out.println(new ElderBrotherLeaveDays().days());
    }
}
