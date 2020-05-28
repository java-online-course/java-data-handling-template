package com.epam.izh.rd.online.service;

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
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/sensitive_data.txt"));
            result = reader.readLine();
            Pattern pattern = Pattern.compile("(\\d{4}\\s)(\\d{4}\\s\\d{4})(\\s\\d{4})");
            Matcher matcher = pattern.matcher(result);
            while (matcher.find()) {
                result = result.replaceAll(matcher.group(2), "**** ****");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/sensitive_data.txt"));
            result = reader.readLine();
            result = result.replaceFirst("\\$\\{\\w+\\}", String.valueOf((int) paymentAmount));
            result = result.replaceFirst("\\$\\{\\w+\\}", String.valueOf((int) balance));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
