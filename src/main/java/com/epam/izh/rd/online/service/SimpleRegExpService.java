package com.epam.izh.rd.online.service;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;
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
        try (FileReader reader = new FileReader("src/main/resources/sensitive_data.txt")){
            int symbol = reader.read();
            char[]buffer = new char [169];
            reader.read(buffer);
            String valueOfchar = String.valueOf(buffer);
            valueOfchar = valueOfchar.replace ("        ", "");
            String line1 = valueOfchar;
            Pattern pat = Pattern.compile("(\\d\\s\\d{1,4}\\s\\d{1,4})\\s");
            Matcher mat = pat.matcher(line1);
            line1 = mat.replaceAll("$1 **** **** ");
            Pattern pat1 = Pattern.compile("(\\d{1,4}\\s\\d{1,4})\\s\\D");
            Matcher mat1 = pat1.matcher(line1);
            line1 = mat1.replaceAll("*");
            return line1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        try (FileReader reader = new FileReader("src/main/resources/sensitive_data.txt")){
            int symbol = reader.read();
            char[]buffer = new char [169];
            reader.read(buffer);
            String valueOfchar = String.valueOf(buffer);
            double d = paymentAmount;
            int i = (int) d;
            double d1 = balance;
            int i1 = (int) d1;
            String paymentAmount1 = String.valueOf(i);
            String balance1 = String.valueOf(i1);
            valueOfchar = valueOfchar.replace ("${payment_amount}", paymentAmount1);
            valueOfchar = valueOfchar.replace ("${balance}", balance1);
            return valueOfchar;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
