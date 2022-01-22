package com.epam.izh.rd.online.service;

import java.util.Locale;

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
        base = base.replace(remove, "");
        return base; //TODO
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     *
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        boolean isQuestionString = false;
        if(text.length() > 0) {
            if(text.charAt(text.length() - 1) == '?') {
                isQuestionString = true;
            }
        }
        return isQuestionString; //TODO
    }

    /**
     * Реализовать функционал соединения переданных строк.
     *
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        String s = "";
        if (elements.length > 0 ) {
            for(String string : elements ){
            s = s + string;
            }
        }
        return s; //TODO
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
        String string = "";
        if (text.length() > 0) {
            String stringLowerCase = text.toLowerCase();
            String stringUpperCase = text.toUpperCase();
            for(int i = text.length()-1; i >=0 ; i--) {
                string = stringUpperCase.charAt(i) + string;
                i--;
                string = stringLowerCase.charAt(i) + string;
            }
        }
        return string; //TODO
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
        boolean isPalindrome = false;
        if (string.length() > 0 ) {
            String stringNoSpace = "";
            for (int i = string.length() - 1 ; i >= 0; i--) {
                if (string.charAt(i) != ' ') {
                    stringNoSpace = string.charAt(i) + stringNoSpace;
                }
            }
            stringNoSpace = stringNoSpace.toLowerCase();

            String reverse = "";
            for (int i = 0; i < stringNoSpace.length(); i++) {
                reverse = stringNoSpace.charAt(i) + reverse;
            }
            isPalindrome = stringNoSpace.equals(reverse);
            }
        return isPalindrome;
    }
}
