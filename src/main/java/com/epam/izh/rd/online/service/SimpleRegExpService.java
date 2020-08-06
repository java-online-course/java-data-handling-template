package com.epam.izh.rd.online.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SimpleRegExpService implements RegExpService {
    @Override
    public String maskSensitiveData() throws IOException {
        return solveMaskSensitiveData();
    }

    @Override
    public String replacePlaceholders(double paymentAmount, double balance) throws IOException {
        return solveReplacePlaceholders(paymentAmount, balance);
    }

    private String solveMaskSensitiveData() throws IOException {
        FileReader fileInputStream = new FileReader("C:/Users/37529/Desktop/EPAM-Tranning/WorkWithData/java-data-handling-template/src/main/resources/sensitive_data.txt");
        int numberOgDigitsLeft = 0;
        int numberOfDigitsChanged = 0;
        int index = 0;
        int symbol;
        char[] finalAnswer = new char[1000];
        while((symbol = fileInputStream.read()) != -1) {
            if((char) symbol >= '0' && (char) symbol <= '9') {
                if(numberOgDigitsLeft != 4) {
                    numberOgDigitsLeft++;
                    finalAnswer[index] = (char) symbol;
                    if(numberOgDigitsLeft == 9) {
                        numberOgDigitsLeft = 0;
                        numberOfDigitsChanged = 0;
                    }
                }
                else {
                    numberOfDigitsChanged++;
                    finalAnswer[index] = '*';
                    if(numberOfDigitsChanged == 8)
                        numberOgDigitsLeft++;
                }
            } else finalAnswer[index] = (char) symbol;
            index++;
        }
        fileInputStream.close();
        return String.valueOf(finalAnswer).trim();
    }

    private String solveReplacePlaceholders(double paymentAmount, double balance) throws IOException {
        FileReader fileInputStream = new FileReader("C:/Users/37529/Desktop/EPAM-Tranning/WorkWithData/java-data-handling-template/src/main/resources/sensitive_data.txt");
        int index = 0;
        int symbol;
        boolean t = false;
        char[] finalString = new char[1000];
        while((symbol = fileInputStream.read()) != -1) {
            if((char) symbol == '$') {
                while((char)(symbol = fileInputStream.read()) != ' ') {}
                if(!t) {
                    char[] string = String.valueOf((int) paymentAmount).toCharArray();
                    for(int i = 0; i < string.length; i++) {
                        finalString[index++] = string[i];
                    }
                    t = true;
                }
                else {
                    char[] string = String.valueOf((int) balance).toCharArray();
                    for(int i = 0; i < string.length; i++) {
                        finalString[index++] = string[i];
                    }
                }
                finalString[index++] = ' ';
            }
            else finalString[index++] = (char) symbol;
        }
        return String.valueOf(finalString).trim();
    }
}
