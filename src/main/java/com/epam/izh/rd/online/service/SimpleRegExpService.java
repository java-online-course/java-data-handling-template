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
        String contentLine = null;
        String fileName = "src/main/resources/sensitive_data.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder builderString = new StringBuilder();
            int ch;

            while ((ch = br.read()) != -1) {
                builderString.append((char) ch);
            }

            contentLine = builderString.toString().trim();
            Pattern pattern = Pattern.compile("\\d{4}\\s(\\d{4}\\s\\d{4})\\s\\d{4}");
            Matcher matcher = pattern.matcher(contentLine);

            while (matcher.find()) {
                contentLine = contentLine.replaceAll(matcher.group(1), "**** ****");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentLine;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String contentLine = null;
        String fileName = "src/main/resources/sensitive_data.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder builderString = new StringBuilder();
            int ch;

            while ((ch = br.read()) != -1) {
                builderString.append((char) ch);
            }

            contentLine = builderString.toString().trim();
            Pattern pattern = Pattern.compile("\\$\\{(\\w+)}");
            Matcher matcher = pattern.matcher(contentLine);

            while (matcher.find()) {
                String group = Pattern.quote(matcher.group());
                String lineRegEx = matcher.group(1).intern();

                if(lineRegEx.equals("payment_amount")) {
                    contentLine = contentLine.replaceAll(group, String.valueOf((int) paymentAmount));
                }
                if(lineRegEx.equals("balance")) {
                    contentLine = contentLine.replaceAll(group, String.valueOf((int) balance));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentLine;
    }
}
