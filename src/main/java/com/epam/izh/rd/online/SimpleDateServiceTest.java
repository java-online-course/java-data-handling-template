package com.epam.izh.rd.online;

import java.time.LocalDate;
import java.time.LocalDateTime;

//TODO Удалить перед отправкой

public class SimpleDateServiceTest {
    public static void main(String[] args) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime today = LocalDateTime.parse("2019-03-27 10:15", dateTimeFormatter);
//        System.out.println(today);
//        System.out.println(localDate.format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
//        System.out.println(simpleDateService.parseDate(localDate));
//        System.out.println(simpleDateService.parseDate(localDate));
//        int year = 2020;
//        int daysOfTheYear = LocalDateTime.of(year, 12, 31,00,00).getDayOfYear();
//        System.out.println(daysOfTheYear);
//        System.out.println(daysOfTheYear *24*60*60);
        LocalDate localDate = LocalDate.now();
        int year = 2020;
        System.out.println(year);
        do {
            year++;
        } while (LocalDateTime.of(year, 12, 31, 0, 0).getDayOfYear() % 366 != 0);
        System.out.println(year);
    }
}
