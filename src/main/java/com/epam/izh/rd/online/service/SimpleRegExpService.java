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
    public String maskSensitiveData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"));
        String content;
        String result = "";
        while ((content = reader.readLine()) != null){
            result += content;
        }
        Pattern pat = Pattern.compile("(\\s\\d{4}\\s)(\\d{4}\\s)(\\d{4}\\s)(\\d{4}\\s)");
        Matcher match = pat.matcher(result);
        result = match.replaceAll("$1**** **** $4");
        return result;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"));
        String content;
        String result = "";
        while ((content = reader.readLine()) != null){
            result += content;
        }
        Pattern patPay = Pattern.compile("\\W\\{.{14}}");
        Matcher match = patPay.matcher(result);
        result = match.replaceAll(String.valueOf((int) paymentAmount));

        Pattern patBalance = Pattern.compile("\\W\\{.{7}}");
        Matcher matchBalance = patBalance.matcher(result);
        result = matchBalance.replaceAll(String.valueOf((int) balance));

        return result;
    }
}
