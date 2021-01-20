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
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            String label = reader.readLine();
            Pattern pattern = Pattern.compile("(\\d{4}) (\\d{4}) (\\d{4}) (\\d{4})");
            Matcher matcher = pattern.matcher(label);
            if(matcher.find()) {
                return matcher.replaceAll("$1 **** **** $4");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
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
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            String label = reader.readLine();
            return label.
                    replaceAll("\\$\\{payment_amount}",String.format("%d",(int)paymentAmount)).
                    replaceAll("\\$\\{balance}",String.format("%d",(int)balance));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
