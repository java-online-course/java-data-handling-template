package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
        try {
            FileReader fileReader = new FileReader("src/main/resources/sensitive_data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine(); //читаем
            String regex = "(\\d{4}\\s)(\\d{4}\\s\\d{4})(\\s\\d{4})"; //что нужно найти
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line); //ищем
            while (matcher.find()) {
                line = line.replaceAll(matcher.group(2), "**** ****"); //меняем
            }
            return line;
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
        try {
            FileReader fileReader = new FileReader("src/main/resources/sensitive_data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine(); //читаем
            BigDecimal payment = new BigDecimal(paymentAmount).setScale(0, RoundingMode.HALF_UP);
            BigDecimal bal = new BigDecimal(balance).setScale(0, RoundingMode.HALF_UP);
            line = line.replaceAll("(\\$\\{(payment_amount)})", String.valueOf(payment));
            line = line.replaceAll("(\\$\\{(balance)})", String.valueOf(bal));
            return line;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
