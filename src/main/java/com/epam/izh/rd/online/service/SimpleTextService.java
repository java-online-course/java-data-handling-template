package com.epam.izh.rd.online.service;

import java.lang.*;


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

            base = base.replace (remove, "");

        return base;
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     *
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {

        return text.endsWith("?");
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
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            newStr = newStr.append(str[i]);
        }
        System.out.println(newStr.toString());
        return newStr.toString();
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
        char[] chArray = text.toCharArray();
        for(int i = 0; i<chArray.length; i++){
            if((i+2) % 2 == 0){
                String str= Character.toString(chArray[i]);
                str = str.toLowerCase();
                char[] chArrayTemp = str.toCharArray();
                chArray[i] = chArrayTemp[0];
            }
            else{
                String str= Character.toString(chArray[i]);
                str = str.toUpperCase();
                char[] chArrayTemp = str.toCharArray();
                chArray[i] = chArrayTemp[0];
            }
        }
        String charToString = new String(chArray);
        return charToString;
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
        boolean result;
        if (string.equals("")){
            result = false;
        }
        else{
            string = string.toLowerCase().replace(" ", "");
            StringBuilder sb = new StringBuilder(string);
            sb = sb.reverse();
            if (string.equals(sb.toString())){
                result = true;
            }
            else result = false;
        }
       return result; //TODO
    }
}
