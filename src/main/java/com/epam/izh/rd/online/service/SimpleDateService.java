package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class SimpleDateService implements DateService {

    @Override
    public String parseDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String stringDate = localDate.format(formatter);
        return stringDate;
    }

    @Override
    public LocalDateTime parseString(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(string, formatter);
        return date;
    }

    @Override
    public String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter) {
        String stringDate = localDate.format(formatter);
        return stringDate;
    }

    @Override
    public long getNextLeapYear() {
        LocalDateTime date = LocalDateTime.now();
        boolean isLeapYear = false;

        while (isLeapYear){
            if ((date.getYear() % 400 == 0) || ((date.getYear() % 4 == 0) && (date.getYear() % 100 != 0))) {
                isLeapYear = true;
            } else {
                date.plusYears(1);
            }
        }
        return date.getYear();
    }

    @Override
    public long getSecondsInYear(int year) {
        return TimeUnit.DAYS.toSeconds(
              Year.of(year).length()
        );
    }


}
