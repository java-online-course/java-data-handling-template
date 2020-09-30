package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        String fileName = "D:/IdeaProjects/java-data-handling-template1/src/main/resources/sensitive_data.txt";
        String value = "";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            value = reader.readLine();
            Pattern pattern = Pattern.compile("(\\d{4}) (\\d{4}) (\\d{4}) (\\d{4})");
            Matcher matcher = pattern.matcher(value);
            if (matcher.find()) {
                value = matcher.replaceAll("$1 **** **** $4");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return value;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String value = readFile();
        value = value.replaceAll("\\$\\{payment_amount}", format(paymentAmount));
        value = value.replaceAll("\\$\\{balance}", format(balance));
//            Pattern pattern = Pattern.compile("\\{\\D+?\\}");
//            Matcher matcher = pattern.matcher(value);
//            if (matcher.find()){
//                    value = matcher.replaceAll(String.valueOf(paymentAmount));
//                    value = matcher.replaceAll(String.valueOf(balance));
//            }
        return value;
    }

    private String readFile() {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("sensitive_data.txt")).getFile());
        String value = "";
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            value = reader.readLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return value;
    }

    private static String format(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }
}
