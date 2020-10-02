package com.epam.izh.rd.online.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleRegExpService implements RegExpService {

    private final String SENSITIVE_DATA_PATTERN = "\\d{4} \\d{4} (?=\\d{4} \\D)";
    private final String SENSITIVE_DATA_REPLACEMENT = "**** **** ";
    private final String PAYMENT_AMOUNT_PATTERN = "\\$\\{payment_amount\\}";
    private final String BALANCE_PATTERN = "\\$\\{balance\\}";

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        return this.readSensitiveFile().replaceAll(SENSITIVE_DATA_PATTERN, SENSITIVE_DATA_REPLACEMENT);
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String sensitiveFileData = this.readSensitiveFile();
        sensitiveFileData = sensitiveFileData.replaceAll(PAYMENT_AMOUNT_PATTERN, String.valueOf((int)paymentAmount));
        return sensitiveFileData.replaceAll(BALANCE_PATTERN, String.valueOf((int)balance));
    }

    private String readSensitiveFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try(FileReader fileReader = new FileReader(this.getResourcePath("sensitive_data.txt"))) {
            while (fileReader.ready()) {
                stringBuilder.append((char) fileReader.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private String getResourcePath(String resource) {
        return this.getClass().getClassLoader().getResource(resource).getPath();
    }
}
