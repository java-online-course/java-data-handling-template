package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleDateService implements DateService {

    /**
     * Метод парсит дату в строку
     *
     * @param localDate дата
     * @return строка с форматом день-месяц-год(01-01-1970)
     */
    @Override
    public String parseDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
    }

    /**
     * Метод парсит строку в дату
     *
     * @param string строка в формате год-месяц-день часы:минуты (1970-01-01 00:00)
     * @return дата и время
     */
    @Override
    public LocalDateTime parseString(String string) {
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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
        //Неясно следующий високосный год от чего (от какой даты)??
        //Либо метод должен принимать int year, либо LocalDate.now(), судя по проверке, на 2020 год, это все таки LocalDate.now()
        //Делаю LocalDate.now()
        //да и похоже те, кто будут делать это задание в 2020, не пройдут, в проверке, как я понял, 2020 год, а следующий високосный после 2020 - 2024
        int year = LocalDate.now().getYear(); //Узнаем текущий год
        do {
            year++;
        } //Если год делиться на 366 без остатка, он високосный
        while (LocalDateTime.of(year, 12, 31, 0, 0).getDayOfYear() % 366 != 0);

        return year;
    }

    /**
     * Метод считает число секунд в заданном году
     *
     * @return число секунд
     */
    @Override
    public long getSecondsInYear(int year) {
        //Узнаем сколько дней в году year, используя getDayOfYear и умножаем на 24 часа, 60 минут, 60 секунд
        return LocalDateTime.of(year, 12, 31, 0, 0).getDayOfYear() * 24 * 60 * 60;
    }


}
