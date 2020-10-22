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
        StringBuffer modifiedText = new StringBuffer();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/main/resources/sensitive_data.txt"))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }

            Pattern pattern = Pattern.compile("\\d{4} (\\d{4} \\d{4}) \\d{4}");
            Matcher matcher = pattern.matcher(stringBuilder);
            while (matcher.find()) {
                String maskedPattern = matcher.group().replaceAll(matcher.group(1), "**** ****");
                matcher.appendReplacement(modifiedText, maskedPattern);
            }
            matcher.appendTail(modifiedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modifiedText.toString();
    }


    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        StringBuilder modifiedText = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/main/resources/sensitive_data.txt"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                modifiedText.append(line);
                line = bufferedReader.readLine();
            }

            modifiedText.replace(0, modifiedText.length(), modifiedText.toString().replaceAll(
                    "\\$\\{payment_amount}", String.valueOf((int) paymentAmount)));
            modifiedText.replace(0, modifiedText.length(), modifiedText.toString().replaceAll(
                    "\\$\\{balance}", String.valueOf((int) balance)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modifiedText.toString();
    }

}
