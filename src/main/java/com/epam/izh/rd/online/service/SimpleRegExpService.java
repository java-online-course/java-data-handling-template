package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
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
        String path = "src/main/resources/sensitive_data.txt";
        String text = null;
        String newText = null;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            text = br.readLine();
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
        Pattern pattern = Pattern.compile("(\\s\\d{4}\\s)\\d{4}\\s\\d{4}\\s(\\d{4}\\s)"); //("[0-9]\\s[\\d]{4}\\s[\\d]{4}\\s")
        Matcher matcher = pattern.matcher(text);
        newText = matcher.replaceAll("$1**** **** $2");
        return newText;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String path = "src/main/resources/sensitive_data.txt";
        String text = null;
        String newText = null;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            text = br.readLine();
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }

        String stringPaymentAmount = String.valueOf((int) paymentAmount);
        Pattern paymentPattern = Pattern.compile("\\$\\{payment_amount\\}");
        Matcher paymentMatcher = paymentPattern.matcher(text);
        newText = paymentMatcher.replaceFirst(stringPaymentAmount);

        String stringBalance = String.valueOf((int) balance);
        Pattern balancePattern = Pattern.compile("\\$\\{balance\\}");
        Matcher balanceMatcher = balancePattern.matcher(newText);
        newText = balanceMatcher.replaceFirst(stringBalance);

        return newText;
    }
}

