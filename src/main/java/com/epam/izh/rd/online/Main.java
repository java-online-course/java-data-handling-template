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

        String text = "N роза упала на лапу Азора";

        String stringLowerCase = text.toLowerCase();
        String stringUpperCase = text.toUpperCase();
        String string = "";
        for(int i = text.length()-1; i >=0 ; i--) {
            string = stringUpperCase.charAt(i) + string;
            i--;
            string = stringLowerCase.charAt(i) + string;
        }

        System.out.println(string);


    }

}
