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
        String line;
        StringBuilder string = new StringBuilder();
        try (FileReader reader = new FileReader("src\\main\\resources\\sensitive_data.txt")) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {
                string.append(line);
            }
            Pattern pattern = Pattern.compile("(\\d{4}\\s){4}");
            Matcher matcher = pattern.matcher(string);
            while (matcher.find()) {
                String str = matcher.group();
                String[] strings = str.split(" ");
                String result = strings[0] + " **** **** " + strings[3] + " ";
                string.replace(matcher.start(), matcher.end(), result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string.toString();
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String line;
        StringBuilder str = new StringBuilder();
        try (FileReader reader = new FileReader("src\\main\\resources\\sensitive_data.txt")) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {
                str.append(line);
            }
            Pattern pattern1 = Pattern.compile("\\$\\{payment_amount}");
            Matcher matcher1 = pattern1.matcher(str);
            while (matcher1.find()) {
                str.replace(matcher1.start(), matcher1.end(), String.valueOf(((int) paymentAmount)));
                matcher1 = pattern1.matcher(str);
            }
            Pattern pattern2 = Pattern.compile("\\$\\{balance}");
            Matcher matcher2 = pattern2.matcher(str);
            while (matcher2.find()) {
                str.replace(matcher2.start(), matcher2.end(), String.valueOf(((int) balance)));
                matcher2 = pattern1.matcher(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
