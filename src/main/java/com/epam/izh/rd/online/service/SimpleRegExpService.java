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

        String str = "";

        try (InputStream file = this.getClass().getResourceAsStream("/sensitive_data.txt");
             BufferedReader obj = new BufferedReader(new InputStreamReader(file));) {

            String text = obj.readLine();

            Pattern pat = Pattern.compile("[0-9]{4} ([0-9]{4} [0-9]{4}) [0-9]{4}");
            Matcher mat = pat.matcher(text);

            while (mat.find()) {

                String num = mat.group(1);
                text = text.replace(num, "**** ****");
                str = text;
            }

        } catch (Exception e) {
        }
        return str;

    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {

        String str = "";

        try (InputStream file = this.getClass().getResourceAsStream("/sensitive_data.txt");
             BufferedReader obj = new BufferedReader(new InputStreamReader(file));) {

            String text = obj.readLine();

            Pattern pat = Pattern.compile("(payment.+amount.).+(balance.)");
            Matcher mat = pat.matcher(text);

            int amount = (int)paymentAmount;
            int bal = (int)balance;


            while (mat.find()) {

                text = text.replaceAll(".."+ mat.group(1), amount+"");
             str = text.replaceAll(".."+ mat.group(2), bal+"");

            }

        } catch (Exception e) {
        }

        return str;
    }
}
