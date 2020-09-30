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
        StringBuilder sb  = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\sensitive_data.txt"))) {
            while (reader.ready()){
                sb.append(reader.readLine());
            }

            Pattern pattern = Pattern.compile(" [\\d]{4} [\\d]{4} [\\d]{4} [\\d]{4} ");
            Matcher matcher = pattern.matcher(sb.toString());
            while (matcher.find()){
                int startIndex = matcher.start();
                sb.replace(startIndex + 6, startIndex + 15, "**** ****");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        StringBuilder sb =  new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\sensitive_data.txt"))) {
            while (reader.ready()){
                String str =reader.readLine().replaceAll("\\$\\{payment_amount}", String.valueOf((int)(paymentAmount)) );
                sb.append(str.replaceAll("\\$\\{balance}", String.valueOf((int)balance)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
