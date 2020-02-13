package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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

        String pathToFile = "";

        URL url = getClass().getClassLoader().getResource("sensitive_data.txt");
        try {
            Path pathAbsolut = Paths.get(url.toURI());
            pathToFile = pathAbsolut.toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        StringBuilder lines = new StringBuilder();
        String text = "";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathToFile))) {

            String line;
            while ((line = reader.readLine()) != null)
                lines.append(line);

        } catch (IOException ignored) {

        }

        text = lines.toString();

        Pattern patternFullCard = Pattern.compile("(\\d{4}[\" \"]){3}\\d{4}");

        Matcher matcher = patternFullCard.matcher(text);

        while (matcher.find()) {
            String originalText = matcher.group();
            String substr = originalText.substring(5,14);
            String mutatedText = originalText.replace(substr, "**** ****");
            text= text.replace(originalText, mutatedText);
        }


        return text;
    }
    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {

        String pathToFile = "";

        URL url = getClass().getClassLoader().getResource("sensitive_data.txt");
        try {
            Path pathAbsolut = Paths.get(url.toURI());
            pathToFile = pathAbsolut.toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        StringBuilder lines = new StringBuilder();
        String text = "";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathToFile))) {

            String line;
            while ((line = reader.readLine()) != null)
                lines.append(line);

        } catch (IOException ignored) {

        }

        text = lines.toString();

        Pattern patternAmount= Pattern.compile("\\$\\{\\b(\\w*payment_amount)\\}");
        Matcher matcherAmount = patternAmount.matcher(text);
        int integerAmount  = (int) paymentAmount;
        String newStr = (String) matcherAmount.replaceAll(String.valueOf(integerAmount));
        text= text.replace(text, newStr);

        Pattern patternBalance= Pattern.compile("\\$\\{\\b(\\w*balance)\\}");
        Matcher matcherBalance = patternBalance.matcher(text);
        int integerBalance  = (int) balance;
        text= text.replace(text,matcherBalance.replaceAll(String.valueOf(integerBalance)));

        return text;
    }
}
