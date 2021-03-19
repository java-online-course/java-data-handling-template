package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
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
        String msg = readFile();
        Pattern aPattern = Pattern.compile("(\\d{4}\\s)(\\d{4}\\s\\d{4})(\\s\\d{4})");
        Matcher aMatcher = aPattern.matcher(msg);
        while (aMatcher.find()) {
            msg = msg.replaceAll(aMatcher.group(2), "**** ****");
        }
        return msg;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String readFile = readFile();
        BigDecimal pay = new BigDecimal(paymentAmount).setScale(0, RoundingMode.HALF_UP);
        BigDecimal bal = new BigDecimal(balance).setScale(0, RoundingMode.HALF_UP);
        readFile = readFile.replaceAll("(\\$\\{(payment_amount)\\})", String.valueOf(pay));
        readFile = readFile.replaceAll("(\\$\\{(balance)\\})", String.valueOf(bal));
        return readFile;
    }

    public String readFile() {
        String line = null;
        try (BufferedReader aBuffer = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            line = aBuffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
