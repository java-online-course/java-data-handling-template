package com.epam.izh.rd.online;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main( String args[]) {

        String location = "src/main/resources/sensitive_data.txt";
        String string = "";

        try (Scanner scanner = new Scanner(
                new InputStreamReader(new FileInputStream(location), "UTF-8"));
        ) {
            while (scanner.hasNextLine()) {
                string = scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern patternCard = Pattern.compile("\\d{4}+\\s+\\d{4}+\\s+\\d{4}+\\s+\\d{4}");
        Matcher matcherCard = patternCard.matcher(string);

        while (matcherCard.find()) {
            String numberCard = string.substring(matcherCard.start(), matcherCard.end());

            Pattern numbersReplace = Pattern.compile("\\s+\\d{4}+\\s+\\d{4}+\\s");
            Matcher matcherReplace = numbersReplace.matcher(numberCard);

            String anonNumberCard = matcherReplace.replaceAll(" **** **** ");
            string = string.replace(numberCard, anonNumberCard);
        }

        System.out.println(string);







    }

}
