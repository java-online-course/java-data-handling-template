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
        double paymentAmount = 12.0;
        double balance = 440.0;

        String paymentAmountString = String.valueOf(paymentAmount);
        String balanceString = String.valueOf(balance);

        Pattern patternPayment = Pattern.compile("[$]+[{]+[a-zA-Z]+[_]+[a-zA-Z]+[}]");
        Matcher matcherPayment = patternPayment.matcher(string);
        string = matcherPayment.replaceAll(paymentAmountString);

        System.out.println(string);
        Pattern patternBalance = Pattern.compile("[$]+[{]+[a-zA-Z]+[}]");
        Matcher matcherBalance = patternBalance.matcher(string);
        string = matcherBalance.replaceAll(balanceString);
        System.out.println(string);

    }

}
