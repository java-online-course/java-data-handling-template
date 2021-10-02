package com.epam.izh.rd.online.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
        String originalText = "";
        try {
            List<String> list = Files.readAllLines(Paths.get("src/main/resources/sensitive_data.txt"));
            for (String string : list) {
                originalText += string;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile("(\\d{4}) (\\d{4}) (\\d{4}) (\\d{4})");
        Matcher matcher = pattern.matcher(originalText);
        if(matcher.find()) {
            return matcher.replaceAll("$1 **** **** $4");
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
        String originalText = "";
        try {
            List<String> list = Files.readAllLines(Paths.get("src/main/resources/sensitive_data.txt"));
            for (String string : list) {
                originalText += string;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        originalText = originalText.replaceAll("[$]\\{[a-z]+_[a-z]+\\}", String.valueOf((int)paymentAmount));
        originalText = originalText.replaceAll("[$]\\{[a-z]+\\}", String.valueOf((int)balance));

        return originalText;
    }
}
