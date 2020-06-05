package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        try ( BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            Pattern pattern = Pattern.compile("\\d{4} \\d{4} \\d{4} \\d{4}");
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String maskedReplacement = matcher.group().substring(0, 5) +
                            "**** ****" + matcher.group().substring(14, 19);
                    line = line.replace(matcher.group(), maskedReplacement);
                }
                stringBuilder.append("\n" + line);
            }
            return stringBuilder.toString().substring(1);
        } catch (IOException e) {
            e.printStackTrace();
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
        try ( BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\\$\\{payment_amount}",String.valueOf((int) paymentAmount));
                line = line.replaceAll("\\$\\{balance}",String.valueOf((int) balance));
                stringBuilder.append("\n" + line);
            }
            return stringBuilder.toString().substring(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
