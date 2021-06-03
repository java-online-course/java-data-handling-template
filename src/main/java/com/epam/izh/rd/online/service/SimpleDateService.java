package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class SimpleDateService implements DateService {

    @Override
    public String parseDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public LocalDateTime parseString(String string) {
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    @Override
    public long getNextLeapYear() {
        int y = Year.now().getValue();
        while (!Year.of(y).isLeap()) {
            y++;
        }
        return y;
    }

    @Override
    public long getSecondsInYear(int year) {
        if (Year.isLeap(year)) {
            return LocalDate.now().lengthOfYear() * 24 * 3600 + 24 * 3600;
        }
        return LocalDate.now().lengthOfYear() * 24 * 3600;
    }
}