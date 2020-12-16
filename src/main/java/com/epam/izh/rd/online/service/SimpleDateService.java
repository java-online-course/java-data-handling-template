package com.epam.izh.rd.online.service;

import javafx.scene.input.DataFormat;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SimpleDateService implements DateService {

    /**
     * Метод парсит дату в строку
     *
     * @param localDate дата
     * @return строка с форматом день-месяц-год(01-01-1970)
     */
    @Override
    public String parseDate(LocalDate localDate) {
        int day=localDate.getDayOfMonth();
        String sDay="";
        if(day<10){
            sDay="0"+String.valueOf(sDay);
        } else {
            sDay=String.valueOf(day);
        }
        int month=localDate.getMonthValue();
        String sMonth="";
        if(month<10){
            sMonth="0"+String.valueOf(month);
        } else {
            sMonth=String.valueOf(month);
        }
        int year=localDate.getYear();
        String s = sDay + "-" + sMonth + "-" + year;
        return s;
    }

    /**
     * Метод парсит строку в дату
     *
     * @param string строка в формате год-месяц-день часы:минуты (1970-01-01 00:00)
     * @return дата и время
     */
    @Override
    public LocalDateTime parseString(String string) {
       // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime ;

            String[] s2 = string.split("\\D");
            int[] s= new int[s2.length];
            for(int i=0; i<s2.length; i++){
                s[i]=Integer.parseInt(s2[i]);
            }
            localDateTime = LocalDateTime.of(s[0],s[1],s[2],s[3], s[4]);

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
        String string = localDate.format(formatter);
        return string;
    }

    /**
     * Метод получает следующий високосный год
     *
     * @return високосный год
     */
    @Override
    public long getNextLeapYear() {
        LocalDate localDate =LocalDate.now();
        long year= localDate.getYear();
        while ( year% 4 !=0){
            year ++;
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
        LocalDateTime start = LocalDateTime.of(year, 1,1,0,0);
        LocalDateTime finish = LocalDateTime.of(year,12,31,23,59,59,59);
        long ms= start.until(finish, ChronoUnit.SECONDS)+1;
        return ms;
    }


}
