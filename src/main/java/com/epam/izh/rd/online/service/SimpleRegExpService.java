package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
        String regex = "(\\d+)(\\s\\d+)(\\s\\d+)\\s(\\d+)";
        String replacement = "$1 **** **** $4";
        String str = "";
        try {
            BufferedReader reader = new BufferedReader( new FileReader(
                    new File("src/main/resources/sensitive_data.txt")));
            str = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.replaceAll(regex,replacement);
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String regex1 = "${payment_amount}";
        String regex2 = "${balance}";
        String str = "";
        try {
            BufferedReader reader = new BufferedReader( new FileReader(
                    new File("src/main/resources/sensitive_data.txt")));
            str = reader.readLine();
            str = str.replace(regex1,String.valueOf( (int) paymentAmount));
            str = str.replace(regex2,String.valueOf( (int) balance));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
