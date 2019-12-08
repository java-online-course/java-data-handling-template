package com.epam.izh.rd.online.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
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
        String formattedDate = formatter.format(localDate);
        return formattedDate;



    }

    /**
     * Метод парсит строку в дату
     *
     * @param string строка в формате год-месяц-день часы:минуты (1970-01-01 00:00)
     * @return дата и время
     */
    @Override
        public LocalDateTime parseString(String string) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Date date=null;
        LocalDateTime localDateTime= null;
        //             TemporalAccessor temp = format.parse(string);
        localDateTime = LocalDateTime.parse(string,format);


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

        String formattedDate = formatter.format(localDate);
        return formattedDate;
    }

    /**
     * Метод получает следующий високосный год
     *
     * @return високосный год
     */
    @Override
    public long getNextLeapYear() {
        LocalDate localDate = LocalDate.now();
        localDate = LocalDate.of(localDate.getYear(),2,localDate.getDayOfMonth());
        for (int i = 0;i<5;i++){
            LocalDate month = localDate.with(TemporalAdjusters.lastDayOfMonth());
            if (month.getDayOfMonth()==29){
                return localDate.getYear();
            }else{
               localDate= localDate.plusYears(1);
            };

        }



        return 0;
    }

    /**
     * Метод считает число секунд в заданном году
     *
     * @return число секунд
     */
    @Override
    public long getSecondsInYear(int year) {
        LocalDateTime start = LocalDateTime.of(year, 1,1,0,0);
        LocalDateTime finish = LocalDateTime.of(year,12,31,23,59,59,59);
        finish=finish.plusNanos(1);
        ZoneOffset offset = ZoneOffset.of("-00:00");
        Instant startInstant = start.toInstant(offset);
        Instant finishInstant = finish.toInstant(offset);
        return startInstant.until(finishInstant, ChronoUnit.SECONDS)+1;

    }


}
