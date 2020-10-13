package org.example.time;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ElderBrotherLeaveDays {

    private final LocalDate leaveDay = LocalDate.of(2019, 5, 17);

    public long days() {
        return ChronoUnit.DAYS.between(leaveDay, LocalDate.now());
    }

    public static void main(String[] args) {
        System.out.println(new ElderBrotherLeaveDays().days());
    }
}
