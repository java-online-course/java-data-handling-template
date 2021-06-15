package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.File;
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
        File file = new File("src/main/resources/sensitive_data.txt");
        String text = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            text = reader.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
        String regex = "\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        String cardNumber;
        String[] numberParts;
        while (matcher.find()) {
            cardNumber = matcher.group();
            numberParts = cardNumber.split(" ");
            text = text.replaceAll(cardNumber,numberParts[0] + " **** **** " + numberParts[3]);
        }
        return text;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String text = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            text = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern pattern = Pattern.compile("\\$\\{.*?}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            if (matcher.group().equals("${payment_amount}")) {
                text = text.replaceAll("\\$\\{payment_amount}", "" + (int)paymentAmount);
            } else if (matcher.group().equals("${balance}")) {
                text = text.replaceAll("\\$\\{balance}", "" + (int)balance);
            }
        }
        return text;
    }
}
