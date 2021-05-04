package com.epam.izh.rd.online.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SimpleDateService implements DateService {

    /**
     * Метод парсит дату в строку
     *
     * @param localDate дата
     * @return строка с форматом день-месяц-год(01-01-1970)
     */
    @Override
    public String parseDate(LocalDate localDate) {
        String result = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return result;
    }

    /**
     * Метод парсит строку в дату
     *
     * @param string строка в формате год-месяц-день часы:минуты (1970-01-01 00:00)
     * @return дата и время
     */
    @Override
    public LocalDateTime parseString(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime result = LocalDateTime.parse(string, formatter);
        return result;
    }

    /**
     * Метод конвертирует дату в строку с заданным форматом
     *
     * @param localDate исходная дата
     * @param formatter формат даты
     * @return полученная строка
     */
    @Override
    public String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter) {
        String result = localDate.format(formatter);
        return result;
    }

    /**
     * Метод получает следующий високосный год
     *
     * @return високосный год
     */
    @Override
    public long getNextLeapYear() {
        Year ldNow = Year.now();
        String year = ldNow.format(DateTimeFormatter.ofPattern("yyyy"));
        Long result = Long.parseLong(year);
        while (true) {
            if (Year.isLeap(result)) {
                return result;
            } else
                result++;
        }
    }

    /**
     * Метод считает число секунд в заданном году
     *
     * @return число секунд
     */
    @Override
    public long getSecondsInYear(int year) {
        Year yea = Year.of(year);
        if (yea.isLeap()) {
            return (long) 366 * 24 * 3600;
        } else
            return (long) 365 * 24 * 3600;
    }


}
