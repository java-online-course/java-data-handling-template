package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData()  {
        String fileName = "src/main/resources/sensitive_data.txt";
        String s[];
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while( ( line = reader.readLine() ) != null ) {
                stringBuilder.append( line );
            }
            s = stringBuilder.toString().split(" ");
            for (int i = 0; i<s.length;i++){
                if (s[i].matches("\\d{4}")) {
                    s[i+1]="****";
                    s[i+2]="****";
                    i=i+4;
                }
            }
            fileName="";
            for (int i = 0; i<s.length;i++){
                fileName+=s[i]+" ";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {

        String fileName = "src/main/resources/sensitive_data.txt";
        String s[];
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while( ( line = reader.readLine() ) != null ) {
                stringBuilder.append( line );
            }
            s = stringBuilder.toString().split(" ");
            for (int i = 0; i<s.length;i++){
                if (s[i].equals("${payment_amount}")) {
                   s[i]=String.valueOf((int)paymentAmount);
                }
                if (s[i].equals("${balance}")) {
                    s[i]=String.valueOf((int)balance);
                }
            }
            fileName="";
            for (int i = 0; i<s.length;i++){
                fileName+=s[i]+" ";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
    }

