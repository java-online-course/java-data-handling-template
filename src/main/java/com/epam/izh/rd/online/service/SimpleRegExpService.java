package com.epam.izh.rd.online.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        File text = new File("src/main/resources/sensitive_data.txt");
        String inText = null;
        try {
            inText = Files.lines(Paths.get(String.valueOf(text.toPath()))).reduce("", String::concat);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inText.replace("\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}", "\\d\\d\\d\\d\\s****\\s****\\s\\d\\d\\d\\d");
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        File text = new File("src/main/resources/sensitive_data.txt");
        String inText = null;
        try {
            inText = Files.lines(Paths.get(String.valueOf(text.toPath()))).reduce("", String::concat);
        } catch (IOException e) {
            e.printStackTrace();
        }
        inText = inText.replace("${payment_amount}", Double.toString(paymentAmount));
        inText = inText.replace("${balance}", Double.toString(balance));
        return inText;
    }
}
