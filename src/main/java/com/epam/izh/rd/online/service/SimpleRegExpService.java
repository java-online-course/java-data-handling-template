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
        String allMaskedText = "";
        try (FileReader fileReader = new FileReader("src/main/resources/sensitive_data.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String allText = bufferedReader.readLine();
            Pattern pattern = Pattern.compile("\\b([0-9]{4})\\s[0-9]{0,9}\\s[0-9]{0,9}\\s([0-9]{4})\\b");
            Matcher matcher = pattern.matcher(allText);
            String maskForCard = "$1 **** **** $2";
            if (matcher.find()) {
                allMaskedText = matcher.replaceAll(maskForCard);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allMaskedText;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String replacePlaceholderAllText = "";
        try (FileReader fileReader = new FileReader("src/main/resources/sensitive_data.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String allText = bufferedReader.readLine();
            Pattern patternForBalance = Pattern.compile("\\$\\{balance\\}");
            Matcher matcherBalance = patternForBalance.matcher(allText);
            String balanceReplace = String.format("%.0f", balance);
            String paymentReplace = String.format("%.0f", paymentAmount);
            if (matcherBalance.find()) {
                String replacePlaceholderBalance = matcherBalance.replaceAll(balanceReplace);
                Pattern patternForPayment = Pattern.compile("\\$\\{payment_amount\\}");
                Matcher matcherPayment = patternForPayment.matcher(replacePlaceholderBalance);
                if (matcherPayment.find()) {
                    replacePlaceholderAllText = matcherPayment.replaceAll(paymentReplace);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return replacePlaceholderAllText;
    }
}
