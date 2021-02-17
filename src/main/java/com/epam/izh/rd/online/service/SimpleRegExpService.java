package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
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
    private static final String nameFile = "D:\\Epam_Java_Course\\java-data-handling-template\\src\\main\\" +
            "resources\\sensitive_data.txt";

    @Override
    public String maskSensitiveData() {

        StringBuilder outputString = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))) {

            outputString = new StringBuilder(reader.readLine());
            Pattern pattern = Pattern.compile("(\\d{4} ){4}");
            Matcher matcher =  pattern.matcher(outputString);

            while (matcher.find()) {
                outputString.replace(matcher.start() + 5, matcher.end() - 5, "**** **** ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Objects.requireNonNull(outputString).toString();
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {

        StringBuilder outputString = null;


        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))) {
            outputString = new StringBuilder(reader.readLine());
            Pattern pattern = Pattern.compile("(\\$\\{)payment_amount}");
            Matcher matcher =  pattern.matcher(outputString);
            if (!matcher.find()) throw new IOException();
            outputString.replace(matcher.start(), matcher.end(), Integer.toString((int) paymentAmount));
            pattern = Pattern.compile("(\\$\\{)balance}");
            matcher =  pattern.matcher(outputString);
            if (!matcher.find()) throw new IOException();
            outputString.replace(matcher.start(), matcher.end(), Integer.toString((int) balance));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Objects.requireNonNull(outputString).toString();
    }

}
