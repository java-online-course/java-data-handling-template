package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"));
            String s = br.readLine();
            br.close();

            Pattern p = Pattern.compile("(\\d{4})\\s(\\d{4})\\s(\\d{4})\\s(\\d{4})");
            Matcher m = p.matcher(s);
            if (m.find()) {
                result =  m.replaceAll("$1 **** **** $4");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String result = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"));
            String s = bf.readLine();
            bf.close();

            Pattern p = Pattern.compile("\\W\\Wpayment_amount\\W");
            Matcher m = p.matcher(s);
            if (m.find()) {
                result = m.replaceAll(Integer.toString((int)paymentAmount));
            }

            p = Pattern.compile("\\W\\Wbalance\\W");
            m = p.matcher(result);
            if (m.find()) {
                result = m.replaceAll(Integer.toString((int)balance));
            }


        } catch (IOException e) {
            e.getStackTrace();
        }

        return result;
    }
}
