package com.epam.izh.rd.online.service;

import java.io.*;
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
        File file = new File("D:\\Develop\\java-data-handling-template\\src\\main\\resources\\sensitive_data.txt");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = bufferedReader.readLine();
            StringBuffer sb = new StringBuffer();

            String regx = "((\\d+.)(\\d+.\\d+.)(\\d+.))";
            Pattern p = Pattern.compile(regx);
            Matcher m = p.matcher(str);

            while (m.find()){
                String s = m.group().replaceAll("(\\d{4}.)(\\d{4}.\\d{4}.)(\\d{4})", "$1**** **** $3");
                m.appendReplacement(sb,s);
            }
            m.appendTail(sb);

            String rez1 = sb.toString();
            return rez1;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
        try {
            File file = new File("D:\\Develop\\java-data-handling-template\\src\\main\\resources\\sensitive_data.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str = bufferedReader.readLine();

            String bal = Integer.toString((int)balance);
            String pay = Integer.toString((int)paymentAmount);

            Pattern p = Pattern.compile("(\\Q${\\E)(\\w+)(\\Q}\\E)");
            Matcher m = p.matcher(str);
            StringBuffer sb = new StringBuffer();

            while (m.find()){
                String search = m.group(2);
                if (search.equals("balance")){
                    m.appendReplacement(sb,bal);
                }
                if (search.equals("payment_amount")){
                    m.appendReplacement(sb,pay);
                }
            }

            m.appendTail(sb);
            String rez = sb.toString();
            return rez;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}