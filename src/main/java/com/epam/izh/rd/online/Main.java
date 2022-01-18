package com.epam.izh.rd.online;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

    public static void main( String args[]) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int currentYear = cal.get(Calendar.YEAR);
        int nextLeapYear = currentYear;
        while (!Year.isLeap(nextLeapYear) ) {
            nextLeapYear++;
        }
        boolean leap = Year.isLeap(nextLeapYear) ;
        System.out.println(leap + " " + nextLeapYear);
    }


}
