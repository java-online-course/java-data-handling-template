package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
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
        String path = Objects.requireNonNull(getClass().getClassLoader()
                .getResource("sensitive_data.txt")).getPath();
        Pattern pattern = Pattern.compile("\\d{4}\\s(\\d{4}\\s\\d{4})\\s\\d{4}");
        StringBuffer buf = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String readStr = reader.readLine();
            Matcher matcher = pattern.matcher(readStr);
            while (matcher.find()) {
                matcher.appendReplacement(buf, matcher.group().
                        replaceAll(matcher.group(1), "**** ****"));
            }
            matcher.appendTail(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String path = Objects.requireNonNull(getClass().getClassLoader()
                .getResource("sensitive_data.txt")).getPath();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();
            line = line.replaceAll("(\\$\\{(payment_amount)})", String.valueOf((int) paymentAmount));
            line = line.replaceAll("(\\$\\{(balance)})", String.valueOf((int) balance));
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
