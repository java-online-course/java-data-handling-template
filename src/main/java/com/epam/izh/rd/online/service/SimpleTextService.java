package com.epam.izh.rd.online.service;

public class SimpleTextService implements TextService {


    @Override
    public String removeString(String base, String remove) {
        return base.replaceAll(remove,"");
    }


    @Override
    public boolean isQuestionString(String text) {
        return text.endsWith("?");
    }


    @Override
    public String concatenate(String... elements) {
        StringBuilder stringBuilder = new StringBuilder(elements.length);
        for (String item:elements
        ) {
            stringBuilder.append(item);
        }
        return stringBuilder.toString();
    }


    @Override
    public String toJumpCase(String text)  {
        char[] myChars = text.toCharArray();
        for (int i = 0; i < myChars.length; i++) {
            if (i % 2 == 0) {
                myChars[i] = Character.toLowerCase(myChars[i]);
            } else {
                myChars[i] = Character.toUpperCase(myChars[i]);
            }
        }
        return String.valueOf(myChars);
    }


    @Override
    public boolean isPalindrome(String string) {
        if (string.length() == 0) {return false;}
        string = string.toLowerCase();
        string = string.replaceAll(" ", "");
        int len = string.length();
        for (int i = 0; i < len/2; i++) {
            if (string.charAt(i) != string.charAt(len-i-1)){return false;}
        }
        return true;
    }


}
