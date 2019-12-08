package com.epam.izh.rd.online.service;

import java.io.*;
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
        File file = new File("src/main/resources/sensitive_data.txt");
        String result = "";
        String line = null;
        int val = 1;
        try( BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
           while ((line=bufferedReader.readLine())!=null){

               Pattern pattern = Pattern.compile("(\\s\\d{4})(\\s\\d{4})(\\s\\d{4})(\\s\\d{4})");
               Matcher matcher = pattern.matcher(line);


               if (matcher.find()){

                      result = matcher.replaceAll("$1 **** ****$4");

                   }



               }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        File file = new File("src/main/resources/sensitive_data.txt");
        String result = "";
        String line = null;
        try( BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((line=bufferedReader.readLine())!=null){
                Pattern patternPA = Pattern.compile("\\W\\{.{14}}");
                Matcher matcherPA = patternPA.matcher(line);

                if (matcherPA.find()){
                    result=matcherPA.replaceAll(String.valueOf((int)paymentAmount));

                }
                Pattern patternB = Pattern.compile("\\W\\{.{7}}");
                Matcher matcherB = patternB.matcher(result);
                if (matcherB.find()){
                    result=matcherB.replaceAll(String.valueOf((int)balance));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
