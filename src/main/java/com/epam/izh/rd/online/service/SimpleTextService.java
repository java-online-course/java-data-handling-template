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
        String rez;
        Pattern pattern = Pattern.compile(remove);
        Matcher matcher = pattern.matcher(base);

        rez=matcher.replaceAll("");
        System.out.print(rez);
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
        Pattern pattern = Pattern.compile("\\?$");
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
           return true;
        }
        return false;
    }

    /**
     * Реализовать функционал соединения переданных строк.
     *
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        String string="";
        for (String s:elements){
            string=string+s;
        }
        return string;
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
        String rez="";
        for(int i=0; i<text.length(); i+=2){
            rez=rez+Character.toLowerCase(text.charAt(i));
            rez=rez+Character.toUpperCase(text.charAt(i+1));
        }
        System.out.print(rez);
        return rez;
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
        Pattern pattern = Pattern.compile(" ");
        Matcher matcher = pattern.matcher(string);
        String rez="";
        rez=matcher.replaceAll("");
        String s="";
        for(int i=0; i<rez.length(); i++){
            s=s+Character.toLowerCase(rez.charAt(i));
        }
        System.out.print(rez);
        boolean f=false;
        int j=s.length()-1;
        for (int i=0; i<rez.length()/2; i++){
            if(s.charAt(i)!=s.charAt(j)){
                f=false;
                break;
            }
            f=true;
            j--;
        }

       return f;
    }
}
