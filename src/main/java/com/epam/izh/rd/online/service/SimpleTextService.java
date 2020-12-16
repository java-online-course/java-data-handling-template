package com.epam.izh.rd.online.service;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern p = Pattern.compile(remove);
        Matcher matcher = p.matcher(base);
        return matcher.replaceAll("");

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
       String result ="";
       for (int i=0;i<elements.length;i++){
           result+=elements[i];
       }
       return result;
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
        String[] symbols = new String[text.length()];
        boolean upCase = false;
        String result = "";
        for (int i=0;i<text.length();i++){
            symbols[i]=Character.toString(text.charAt(i));

            if(upCase){
                symbols[i]= symbols[i].toUpperCase();
                upCase=false;
            }else {
               symbols[i]= symbols[i].toLowerCase();
                upCase=true;
            }
            result+=symbols[i];
       }
     return result;
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
        String str = string.toLowerCase();
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(str);

        StringBuilder standardText = new StringBuilder(matcher.replaceAll(""));
        StringBuilder reversText = new StringBuilder(standardText);
        reversText.reverse();
        if (Pattern.matches("\\s*",string)){
            return false;
        }
        return standardText.toString().equalsIgnoreCase(reversText.toString());
    }
}
