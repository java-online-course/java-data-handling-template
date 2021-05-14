package com.epam.izh.rd.online.service;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        try {
            BufferedReader buf = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"));
            StringBuilder line = new StringBuilder(buf.readLine());
            Pattern pattern = Pattern.compile("\\s\\d{4}\\s+(\\d{4}\\s+\\d{4})\\s+\\d{4}");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                line.replace(matcher.start(1), matcher.end(1), "**** ****");

            }

            return String.valueOf(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        BufferedReader buf = null;
        try {
            buf = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
        String s = null;
        try {
            s = buf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String propName = matcher.group(1);
            String propValue;
            switch (propName) {
                case "payment_amount":
                    propValue = String.valueOf((int) paymentAmount);
                    break;
                case "balance":
                    propValue = String.valueOf((int) balance);
                    break;
                default:
                    throw new IllegalArgumentException("Cannot parse input string string");
            }
            s = s.replace(matcher.group(), propValue);
        }
        return s;
    }
}
