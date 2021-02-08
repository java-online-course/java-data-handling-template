package com.epam.izh.rd.online.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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

        try (FileReader con = new FileReader("src/main/resources/sensitive_data.txt")){
            BufferedReader cont = new BufferedReader (con) ;
            String text = cont.readLine();
            StringBuilder file = new StringBuilder(text);
            Pattern pat = Pattern.compile("\\s*\\d{4}\\s+(\\d{4}\\s+\\d{4})\\s+\\d{4}") ;
            Matcher mat = pat.matcher(file) ;
            while (mat.find()) {
                file.replace(mat.start(1),mat.end(1),"**** ****") ;
            }
            return file.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String payment = "${payment_amount}";
        String balance1 = "${balance}";
        String pay = String.valueOf((int)paymentAmount);
        String bal = String.valueOf((int)balance) ;
        Path path = Paths.get("src/main/resources/sensitive_data.txt");
        try {
          String path1 = new String(Files.readAllBytes(path)).replace(payment,pay).replace(balance1,bal) ;
           return path1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;
    }
}
