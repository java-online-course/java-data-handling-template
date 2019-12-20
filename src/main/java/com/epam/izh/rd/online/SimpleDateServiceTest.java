package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleDateService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//TODO Удалить перед отправкой

public class SimpleDateServiceTest {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime today = LocalDateTime.parse("2019-03-27 10:15", dateTimeFormatter);
        System.out.println(today);
//        System.out.println(localDate.format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
//        System.out.println(simpleDateService.parseDate(localDate));
//        System.out.println(simpleDateService.parseDate(localDate));
    }
}
