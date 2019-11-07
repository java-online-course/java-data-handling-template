package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DateService {

    /**
     * Метод парсит дату в строку
     * @param localDate дата
     * @return строка с форматом день-месяц-год
     */
    String parseDate(LocalDate localDate);

    /**
     * Метод парсит строку в дату
     * @param string строка в формате год-месяц-день час:минута
     * @return дата и время
     */
    LocalDateTime parseString(String string);

    /**
     * Метод конвертирует дату в строке в строку с заданным форматом
     * @param string исходная строка
     * @param formatter формат даты
     * @return полученная строка
     */
    String convertToAnotherFormat(String string, DateTimeFormatter formatter);

    /**
     * Метод получает следующий високосный год
     * @return високосный год
     */
    long getNextLeapYear();

    /**
     * Метод считает число секунд в 2020 году
     * @return
     */
    long getSecondsInNextMonth();
}
