package com.epam.izh.rd.online.service;

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
        return base.replace(remove, "");
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     *
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        if (text.endsWith("?")) {
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
        String result = "";
        for (String elem: elements) {
            String[] accumulator = {result, elem};
            result = String.join(" ", accumulator).trim();
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
        StringBuffer sb = new StringBuffer(text.length());
        Character letter;
        for (int i = 0; i < text.length(); i++) {
            letter = text.charAt(i);
            if (i % 2 == 0) {
                sb.append(String.valueOf(letter).toLowerCase());
            } else {
                sb.append(String.valueOf(letter).toUpperCase());
            }
        }
        return sb.toString();
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
        if (string.length() < 2) {
            return false;
        }

        String text = string.replace(" ", "");
        int len = text.length();
        for (int i = 0; i < len / 2; i++) {
            String leftPart = String.valueOf(text.charAt(i)).toLowerCase();
            String rightPart = String.valueOf(text.charAt(len - i - 1)).toLowerCase();
            if (leftPart.compareTo(rightPart) != 0) {
                return false;
            }
        }
        return true;
    }
}
