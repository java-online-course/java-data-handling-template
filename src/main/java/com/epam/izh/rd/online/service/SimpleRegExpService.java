package com.epam.izh.rd.online.service;

import java.io.*;
import java.nio.charset.Charset;
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
        StringBuilder result = new StringBuilder();
        String regex = "(\\d{4}\\s){4}";
        Pattern pattern = Pattern.compile(regex);

        String fileName = // "/sensitive_data.txt";
                File.separator + "sensitive_data.txt";

        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset())))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    Matcher matcher = pattern.matcher(line);
                    StringBuilder stringBuilder = new StringBuilder(line);
                    while (matcher.find()) {
                        stringBuilder.replace(matcher.start()+4,matcher.end()-5," **** **** ");
                    }
                    result.append(stringBuilder);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        return result.toString();
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
//        String result = "";
        StringBuilder result = new StringBuilder();
        String regex1 = "\\$\\{payment_amount}";
        Pattern pattern1 = Pattern.compile(regex1);
        String regex2 = "\\$\\{balance}";
        Pattern pattern2 = Pattern.compile(regex2);

        String fileName = // "/sensitive_data.txt";
                File.separator + "sensitive_data.txt";

        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset())))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                line = line.replaceAll(regex1, Integer.toString((int)paymentAmount));
                line = line.replaceAll(regex2, Integer.toString((int)balance));

                result.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
