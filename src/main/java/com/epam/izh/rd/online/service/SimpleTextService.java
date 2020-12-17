package com.epam.izh.rd.online.service;

public class SimpleTextService implements TextService {

    @Override
    public String removeString(String base, String remove) {
        return base.replace(remove, "");
    }

    @Override
    public boolean isQuestionString(String text) {
        if (text.length() == 0) {
            return false;
        }

        return text.substring(text.length() - 1).equals("?");
    }

    @Override
    public String concatenate(String... elements) {
        String result = "";

        for (String element : elements) {
            result += element;
        }

        return result;
    }

    @Override
    public String toJumpCase(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            if (i % 2 == 0) {
                result += text.toLowerCase().charAt(i);
            } else {
                result += text.toUpperCase().charAt(i);
            }
        }

        return result;
    }

    @Override
    public boolean isPalindrome(String string) {
        if (string.length() == 0) {
            return false;
        }

        string = string.replace(" ", "");

        for (int i = 0; i < string.length() / 2; i++) {
            if (string.toLowerCase().charAt(i) != string.toLowerCase().charAt(string.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }
}
