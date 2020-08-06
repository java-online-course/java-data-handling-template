package com.epam.izh.rd.online.service;

import java.util.stream.IntStream;

public class SimpleTextService implements TextService {

    @Override
    public String removeString(String base, String remove) {
        return base.replace(remove, "");
    }

    @Override
    public boolean isQuestionString(String text) {
        return text.endsWith("?");
    }

    @Override
    public String concatenate(String... elements) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : elements) {
            stringBuilder.append(string);
        }
        return String.valueOf(stringBuilder);
    }

    @Override
    public String toJumpCase(String text) {
        return solveToJumpCase(text);
    }

    @Override
    public boolean isPalindrome(String string) {
        return solveIsPalindrome(string.toLowerCase().replaceAll(" ", "").toCharArray());
    }

    private String solveToJumpCase(String text) {
        byte[] bytes = text.getBytes();
        StringBuilder finalString = new StringBuilder();
        for(int i = 0; i < bytes.length; i++) {
            if((char) bytes[i] == ' ')
                finalString.append(' ');
            else {
                if(i % 2 == 0) {
                    if(!(bytes[i] >= 97))
                        finalString.append((char)(bytes[i] + 32));
                    else
                        finalString.append((char) bytes[i]);
                }
                else
                    if(bytes[i] >= 97)
                        finalString.append((char)(bytes[i] - 32));
                    else
                        finalString.append((char) bytes[i]);
            }
        }
        return String.valueOf(finalString);
    }

    private boolean solveIsPalindrome(char[] text) {
        int middle = text.length / 2;
        if (middle == 0)
            return false;
        for(int i = 0; i < middle; i++)
            if(text[i] != text[text.length - i - 1])
                return false;
            return true;
    }
}
