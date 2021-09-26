package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
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
        File file = new File("src/main/resources", "sensitive_data.txt");
        Pattern pattern = Pattern.compile("\\d{4}\\s(\\d{4}?)\\s(\\d{4}?)\\s\\d{4}");
        Matcher matcher;
        int counter = 0;
        try {
            List<String> result = Files.readAllLines(file.toPath());
            while (counter < result.size()){
                matcher = pattern.matcher(result.get(counter));
                while (matcher.find()) {
                    String[] parts = matcher.group().split(" ");
                    result.set(counter, result.get(counter).replaceFirst(String.valueOf(pattern), parts[0] + " **** **** " + parts[parts.length - 1]));
                }
                counter++;
            }
            return result.get(0);
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
        String text = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            text = reader.readLine();
            Pattern pattern = Pattern.compile("\\$\\{.*?}");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                if (matcher.group().equals("${payment_amount}")) {
                    text = text.replaceAll("\\$\\{payment_amount}", String.valueOf((int)paymentAmount));
                } else if (matcher.group().equals("${balance}")) {
                    text = text.replaceAll("\\$\\{balance}", String.valueOf((int)balance));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}