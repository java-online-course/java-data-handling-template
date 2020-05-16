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
        String s = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(
                "D:\\java-data-handling-template\\src\\main\\resources\\sensitive_data.txt"))) {
            s = reader.readLine();
            Pattern patten = Pattern.compile("(\\d{4}\\s){4}");
            Matcher matcher = patten.matcher(s); //строка счета
            while(matcher.find()) {
                    // группа - счет
                    StringBuilder str = new StringBuilder(matcher.group()).replace(5, 14, "**** ****");
                    s = s.replaceAll(matcher.group(), str.toString());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String s ="";
        try (BufferedReader reader = new BufferedReader(new FileReader(
                "D:\\java-data-handling-template\\src\\main\\resources\\sensitive_data.txt"))) {
            s = reader.readLine();
            s = s.replaceAll("\\${1}\\{[a-z]{7}_[a-z]{6}}", String.valueOf((int)paymentAmount));
            s = s.replaceAll("\\${1}\\{[a-z]{7}}", String.valueOf((int)balance));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
