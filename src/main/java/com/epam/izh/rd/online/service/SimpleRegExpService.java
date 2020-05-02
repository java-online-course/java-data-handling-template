package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    @Override
    public String maskSensitiveData() {
        String result = "";

        try (FileReader reader = new FileReader("src/main/resources/sensitive_data.txt");
             BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            Pattern pattern = Pattern.compile("([0-9]+)\\s([0-9]+)\\s([0-9]+)\\s([0-9]+)");
            Matcher matcher = pattern.matcher(bufferedReader.readLine());
            while (matcher.find()) {
                result = matcher.replaceAll("$1 **** **** $4");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String result = "";

        try (FileReader reader = new FileReader("src/main/resources/sensitive_data.txt");
             BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            Pattern pattern = Pattern.compile("\\$\\{[a-z]+_[a-z]+}");
            Matcher matcher = pattern.matcher(bufferedReader.readLine());
            if (matcher.find()) {
                result = matcher.replaceAll(String.valueOf((int)paymentAmount));
            }
            pattern = Pattern.compile("\\$\\{[a-z]+}");
            matcher = pattern.matcher(result);
            if (matcher.find()) {
                result = matcher.replaceAll(String.valueOf((int)balance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
