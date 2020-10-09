package com.epam.izh.rd.online.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     *
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        String rez = base.replaceAll(remove,"");
        return rez;

    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     *
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        if (text.endsWith("?")){
            return true;
        }
        return false; //TODO
    }

    /**
     * Реализовать функционал соединения переданных строк.
     *
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        String[] str = elements;
        String rez= "";
        for (int i = 0; i < str.length; i++) {
            rez = rez + str[i];
        }
        return rez;
        //TODO
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     *
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {
        char[] arrTemp = text.toCharArray();
        for (int i = 0; i < arrTemp.length; i+=2) {
            arrTemp[i] = Character.toLowerCase(arrTemp[i]);
        }
        for (int i = 1; i < arrTemp.length; i+=2) {
            arrTemp[i] = Character.toUpperCase(arrTemp[i]);
        }

        String s = new String(arrTemp);

        return s;
         //TODO
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     *
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     *
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {
        if (string.length()<=1){
            return false;
        }
        StringBuilder sb = new StringBuilder();
        String strEtalon = string.replaceAll("\\s","").toLowerCase();
        sb.append(strEtalon).reverse();
        String strBild = sb.toString();
        if (strEtalon.equals(strBild)){
            return true;
        }else return false;

       //TODO
    }
}
