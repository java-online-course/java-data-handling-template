package com.epam.izh.rd.online.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
        return formatter.format(localDate);
    }

    /**
     * Метод парсит строку в дату
     *
     * @param string строка в формате год-месяц-день часы:минуты (1970-01-01 00:00)
     * @return дата и время
     */
    @Override
    public LocalDateTime parseString(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
        LocalDate date = LocalDate.parse(string, formatter);
        LocalTime time = LocalTime.parse(string,formatter);
        return LocalDateTime.of(date,time);
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
        return formatter.format(localDate);
    }

    /**
     * Метод получает следующий високосный год
     *
     * @return високосный год
     */
    @Override
    public long getNextLeapYear() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        boolean isLeapYear = false;
        int count = 0;
        while (!isLeapYear){
            if(localDate.plusYears(count).getYear()%4==0){
                isLeapYear = true;
            }else {
                count++;
            }
        }
        return localDate.plusYears(count).getYear();
    }

    /**
     * Метод считает число секунд в заданном году
     *
     * @return число секунд
     */
    @Override
    public long getSecondsInYear(int year) {
        if (year % 4==0){
            return 366*24*60*60;
        }else{
            return 365*24*60*60;
        }
    }
}
