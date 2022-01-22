package com.epam.izh.rd.online.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
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
        String location = "src/main/resources/sensitive_data.txt";
        String string = "";

        try (Scanner scanner = new Scanner(
                new InputStreamReader(new FileInputStream(location), "UTF-8"));
        ) {
            while (scanner.hasNextLine()) {
                string = scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern patternCard = Pattern.compile("\\d{4}+\\s+\\d{4}+\\s+\\d{4}+\\s+\\d{4}");
        Matcher matcherCard = patternCard.matcher(string);

        while (matcherCard.find()) {
            String numberCard = string.substring(matcherCard.start(), matcherCard.end());

            Pattern numbersReplace = Pattern.compile("\\s+\\d{4}+\\s+\\d{4}+\\s");
            Matcher matcherReplace = numbersReplace.matcher(numberCard);

            String anonNumberCard = matcherReplace.replaceAll(" **** **** ");
            string = string.replace(numberCard, anonNumberCard);
        }
        return string;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        return null;
    }
}
