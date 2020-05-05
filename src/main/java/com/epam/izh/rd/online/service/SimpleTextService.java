package com.epam.izh.rd.online.service;

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
        base = base.replace (remove, "");
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
        Pattern p = Pattern.compile(".+\\?");
        Matcher m = p.matcher(text);
        return m.matches();
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
        for (int i = 0;i<elements.length; i++){
        s = s.concat(elements[i]);
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
        char[] res = new char[text.length()];
        for (int i = 0; i < text.length(); ++i) {
            char ch = text.charAt(i);
            if (i%2 != 0) {
                ch = Character.toUpperCase(ch);
                res[i] = ch;
            } else {
                ch = Character.toLowerCase(ch);
                res[i] = ch;
            }
        }
        return new String(res);

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
        if (string == ""){
            return false;
        }
        String B="";
        String C=string.replace(" ","");
        char[] mass =C.toCharArray();
        char[] mass2=new char[mass.length];
        for ( int i =mass.length-1; i>= 0;){
            for(int j=0;j<mass2.length; j++){

                mass2[j]=mass[i]; i--;

                B+=mass2[j];

            }

        }
        return B.equalsIgnoreCase(C);
    }
}
