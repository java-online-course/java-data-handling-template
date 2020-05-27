package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String currentDate = localDate.format(formatter);
        return currentDate;
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
        LocalDateTime localDateTime = LocalDateTime.parse(string, formatter);
        return localDateTime;

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
        String currentDate = localDate.format(formatter);
        return currentDate;
    }

    /**
     * Метод получает следующий високосный год
     *
     * @return високосный год
     */
    @Override
    public long getNextLeapYear() {
        int year = 2020;
        long leapYear = -1;
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        for (int i = 0; i < 10; i++) {
            gregorianCalendar.isLeapYear(year);
            i++;
            year++;   // тест не проходит, потому что "assertEquals(2020)",
            // а нужно получить следующий високосный
            //если убрать  year++, то тест пройдет
            if (gregorianCalendar.isLeapYear(year)) {
                leapYear = year;
            }
        }
        return leapYear;
    }

    /**
     * Метод считает число секунд в заданном году
     *
     * @return число секунд
     */
    @Override
    public long getSecondsInYear(int year) {
        // високосный год - 60 секунд * 60 минут * 24 часа * 366 дней
        // обычный год - 60 секунд * 60 минут * 24 часа * 365 дней
        long seconds;
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        if (gregorianCalendar.isLeapYear(year)) {
            seconds = 60 * 60 * 24 * 366;
        } else {
            seconds = 60 * 60 * 24 * 365;
        }
        return seconds;
    }


}
