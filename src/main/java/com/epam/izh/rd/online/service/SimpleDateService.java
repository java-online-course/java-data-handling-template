package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.spi.CalendarNameProvider;

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
        long year = 2020;
        while(!new GregorianCalendar().isLeapYear((int) year)) {
            year++;
        }
        return year;
    }

    /**
     * Метод считает число секунд в заданном году
     *
     * @return число секунд
     */
    @Override
    public long getSecondsInYear(int year) {
        return new GregorianCalendar().isLeapYear(year) ? 31_622_400 : 31_536_000;
    }
}
