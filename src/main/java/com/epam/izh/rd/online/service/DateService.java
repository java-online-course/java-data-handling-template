package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public interface DateService {

    String parseDate(LocalDate localDate);

    TemporalAccessor parseString(String string);

    String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter);

    long getNextLeapYear();

    long getSecondsInYear(int year);
}
