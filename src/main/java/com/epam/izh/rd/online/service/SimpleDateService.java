package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleDateService implements DateService {
    @Override
    public String parseDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
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
        //Неясно следующий високосный год от чего (от какой даты)??
        //Либо метод должен принимать int year, либо LocalDate.now(), судя по проверке, на 2020 год, это все таки LocalDate.now()
        //Делаю LocalDate.now()
        int year = LocalDate.now().getYear(); //Узнаем текущий год
        do {
            year++;
        } //Если год делиться на 366 без остатка, он високосный
        while (LocalDateTime.of(year, 12, 31, 0, 0).getDayOfYear() % 366 != 0);

        return year;
    }

    @Override
    public long getSecondsInYear(int year) {
        //Узнаем сколько дней в году year, используя getDayOfYear и умножаем на 24 часа, 60 минут, 60 секунд
        return LocalDateTime.of(year, 12, 31, 0, 0).getDayOfYear() * 24 * 60 * 60;
    }


}
