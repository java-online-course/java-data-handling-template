package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.repository.SimpleFileRepository;
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
        StringBuilder stringBuilder = new StringBuilder(
                new SimpleFileRepository().readFileFromResources("sensitive_data.txt")
        );

        Pattern pattern = Pattern.compile("((\\d){4}\\s?){4}");
        Matcher matcher = pattern.matcher(stringBuilder.toString());
        while (matcher.find()) {
            stringBuilder.replace(matcher.start() + 4, matcher.end() - 5, " **** **** ");
        }
        return stringBuilder.toString();
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        StringBuilder stringBuilder = new StringBuilder(
                new SimpleFileRepository().readFileFromResources("sensitive_data.txt")
        );

        Pattern pattern = Pattern.compile("[$][{]{1}\\w+[}]{1}");
        Matcher matcher = pattern.matcher(stringBuilder.toString());

        while (matcher.find()) {
            if (stringBuilder.substring(matcher.start() + 2, matcher.end() - 1).equals("payment_amount")) {
                stringBuilder.replace(matcher.start(), matcher.end(), String.format("%.0f", paymentAmount));
                matcher = pattern.matcher(stringBuilder.toString());
            } else if (stringBuilder.substring(matcher.start() + 2, matcher.end() - 1).equals("balance")) {
                stringBuilder.replace(matcher.start(), matcher.end(), String.format("%.0f", balance));
                matcher = pattern.matcher(stringBuilder.toString());
            }
        }
        return stringBuilder.toString();
    }

}
