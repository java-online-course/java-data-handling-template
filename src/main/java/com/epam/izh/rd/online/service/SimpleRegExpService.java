package com.epam.izh.rd.online.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        File f = new File("src\\main\\resources\\sensitive_data.txt");
        String s="";
        String rez="";
        try {
            s= new String(Files.readAllBytes(Paths.get(f.toURI())));
            Pattern pattern = Pattern.compile("(\\s\\d{4})(\\s\\d{4})(\\s\\d{4})(\\s\\d{4})");
            Matcher matcher = pattern.matcher(s.trim());
            if(matcher.find()){
                rez=matcher.replaceAll("$1 **** ****$4");
            }

           // String [] s2= s.split("\\D");

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(rez);
        return rez;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        File f = new File("src\\main\\resources\\sensitive_data.txt");
        String s="";
        String rez="";
        try {
            s= new String(Files.readAllBytes(Paths.get(f.toURI())));
            Pattern pattern1 = Pattern.compile("\\$.payment_amount.");
            Matcher matcher1 = pattern1.matcher(s.trim());
            String s1=Integer.toString((int)paymentAmount);
            rez=matcher1.replaceAll(s1);
            Pattern pattern2 = Pattern.compile("\\$.balance.");
            Matcher matcher2 = pattern2.matcher(rez.trim());
            s1=Integer.toString((int)balance);
            rez=matcher2.replaceAll(s1);

            // String [] s2= s.split("\\D");

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(rez);
        return rez;
    }
}
