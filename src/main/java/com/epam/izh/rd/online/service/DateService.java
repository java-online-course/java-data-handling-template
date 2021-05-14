package com.epam.izh.rd.online.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DateService {

    String parseDate(LocalDate localDate);

    LocalDateTime parseString(String string) throws ParseException;

    String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter);

    long getNextLeapYear();

    long getSecondsInYear(int year);
}
