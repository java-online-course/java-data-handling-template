package com.epam.izh.rd.online.service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SimpleDateService implements DateService {

    /**
     * Метод парсит дату в строку
     *
     * @param localDate дата
     * @return строка с форматом день-месяц-год(01-01-1970)
     */
    @Override
    public String parseDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Метод парсит строку в дату
     *
     * @param string строка в формате год-месяц-день часы:минуты (1970-01-01 00:00)
     * @return дата и время
     */
    @Override
    public LocalDateTime parseString(String string) {
        String[] split = string.split("\\D");

        return LocalDateTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])
                , Integer.parseInt(split[4]), Integer.parseInt(split[4]));
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
        long year = 2019;
        while (true) {
            if (!Year.isLeap(year)) {
                year++;
                break;
            }
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
        LocalDate prevDate = LocalDate.of(year + 1, 1, 1);
        LocalDate currentDate = LocalDate.of(year, 1, 1);

        return prevDate.toEpochSecond(LocalTime.NOON, ZoneOffset.MIN)
                - currentDate.toEpochSecond(LocalTime.NOON, ZoneOffset.MIN);
    }


}
