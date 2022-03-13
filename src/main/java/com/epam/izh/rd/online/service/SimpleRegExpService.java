package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.File;
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
    @Override
    public String maskSensitiveData() {
        StringBuilder result = null;
        try {
            File file = new File("C:/Users/Пользователь/IdeaProjects/java-data-handling-template/src/main/resources/sensitive_data.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            result = new StringBuilder(reader.readLine());
            Pattern pattern = Pattern.compile("(\\d{4} ){4}");
            Matcher matcher =  pattern.matcher(result);

            while (matcher.find()) {
                result.replace(matcher.start() + 5, matcher.end() - 5, "**** **** ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Objects.requireNonNull(result).toString();
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        StringBuilder result = null;
        try  {
            File file = new File("C:/Users/Пользователь/IdeaProjects/java-data-handling-template/src/main/resources/sensitive_data.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            result = new StringBuilder(reader.readLine());
            Pattern pattern = Pattern.compile("(\\$\\{)payment_amount}");
            Matcher matcher =  pattern.matcher(result);
            if (!matcher.find()) throw new IOException();
            result.replace(matcher.start(), matcher.end(), Integer.toString((int) paymentAmount));
            pattern = Pattern.compile("(\\$\\{)balance}");
            matcher =  pattern.matcher(result);
            if (!matcher.find()) throw new IOException();
            result.replace(matcher.start(), matcher.end(), Integer.toString((int) balance));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Objects.requireNonNull(result).toString();
    }
}
