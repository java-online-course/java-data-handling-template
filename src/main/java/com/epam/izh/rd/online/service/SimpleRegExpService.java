package com.epam.izh.rd.online.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        Pattern paymentAmountAndBalance = Pattern.compile("(?<=\\d{4}\\s)(\\d{4}\\s\\d{4})(?=\\s\\d{4})");
        Path sensitivePath = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "sensitive_data.txt");
        String read = "";
        try {
            read = Files.readAllLines(sensitivePath).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Matcher source = paymentAmountAndBalance.matcher(read);
        boolean isMatch = source.find();
        if (isMatch) {
            read = read.replaceAll(paymentAmountAndBalance.pattern().toString(), "\\*\\*\\*\\* \\*\\*\\*\\*");
        }
        return read;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        int roundPaymentAmount = (int) Math.round(paymentAmount);
        int roundBalance = (int) Math.round(balance);
        Pattern paymentAmountAndBalance = Pattern.compile("\\$\\{payment_amount}|\\$\\{balance}");
        String stringPaymentAmount = String.valueOf(roundPaymentAmount);
        String stringBalance = String.valueOf(roundBalance);
        Path sensitivePath = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "sensitive_data.txt");
        String read = "";
        try {
            read = Files.readAllLines(sensitivePath).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Matcher source = paymentAmountAndBalance.matcher(read);
        boolean isMatch = source.find();
        if (isMatch) {
            read = read.replaceAll("\\$\\{payment_amount}", stringPaymentAmount)
                    .replaceAll("\\$\\{balance}", stringBalance);
        }
        return read;
    }
}
