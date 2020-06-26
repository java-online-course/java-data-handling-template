package com.epam.izh.rd.online.service;

import java.io.*;
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
        return findAndReplaceCardNumber(readResourceFileNames("sensitive_data.txt"));
    }

    private StringBuilder readResourceFileNames(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\" + name))) {
            return new StringBuilder(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new StringBuilder();
    }

    private String findAndReplaceCardNumber(StringBuilder text) {
        Pattern pat = Pattern.compile("\\s*\\d{4}\\s+(\\d{4}\\s+\\d{4})\\s+\\d{4}");
        Matcher match = pat.matcher(text);
        while (match.find()) {
            text.replace(match.start(1), match.end(1), "**** ****");
        }
        return text.toString();
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        return findAndReplacePlaceholder(paymentAmount, balance, readResourceFileNames("sensitive_data.txt"));
    }

    private String findAndReplacePlaceholder(double paymentAmount, double balance, StringBuilder text) {
        String x = text.toString();
        Pattern pat = Pattern.compile("(\\$\\{p.*[}]).+(\\$\\{b.*[}])");
        Matcher match = pat.matcher(x);
        if (match.find()) {
            text.replace(match.start(2), match.end(2), String.valueOf((int) balance)).
                    replace(match.start(1), match.end(1), String.valueOf((int) paymentAmount));
        }
        System.out.println(text);
        return text.toString();
    }
}
