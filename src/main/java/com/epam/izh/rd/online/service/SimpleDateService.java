package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class SimpleDateService implements DateService {

    /**
     * Метод парсит дату в строку
     *
     * @param localDate дата
     * @return строка с форматом день-месяц-год(01-01-1970)
     */
    @Override
    public String parseDate(LocalDate localDate) {
        String month = "0";
        if (localDate.getMonthValue() < 10) {
            month = month + localDate.getMonthValue();
        } else {
            month = "" + localDate.getMonthValue();
        }
        return String.format("%d-%2s-%d", localDate.getDayOfMonth(), month, localDate.getYear());
    }


    /**
     * Метод парсит строку в дату
     *
     * @param string строка в формате год-месяц-день часы:минуты (1970-01-01 00:00)
     * @return дата и время
     */
    @Override
    public LocalDateTime parseString(String string) {
        int year = Integer.parseInt(string.substring(0, 4));
        int mount = Integer.parseInt(string.substring(5, 7));
        int days = Integer.parseInt(string.substring(8, 10));
        int hours = Integer.parseInt(string.substring(11, 13));
        int minutes = Integer.parseInt(string.substring(14, 16));
        return LocalDateTime.of(year, mount, days, hours, minutes);
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
        return localDate.format(formatter);
    }

    /**
     * Метод получает следующий високосный год
     *
     * @return високосный год
     */
    @Override
    public long getNextLeapYear() {
        long year = Year.now().getValue();
        do {
            year++;
        } while (!Year.isLeap(year));

        return year;
    }

    /**
     * Метод считает число секунд в заданном году
     *
     * @return число секунд
     */
    @Override
    public long getSecondsInYear(int year) {
        int daysInYear = 365;
        int hoursInDay = 24;
        int minutesInHours = 60;
        int secondsInMinutes = 60;
        if (Year.isLeap(year)) {
            daysInYear = 366;
        }
        return daysInYear * hoursInDay * minutesInHours * secondsInMinutes;
    }


}
