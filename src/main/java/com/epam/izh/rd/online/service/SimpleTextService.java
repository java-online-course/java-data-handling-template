package com.epam.izh.rd.online.service;

public class SimpleTextService implements TextService {
    //TODO удалить все sout

    /**
     * Реализовать функционал удаления строки из другой строки.
     * <p>
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base   - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        //А удалять все вхождения или первое?? не написано! Case учитывать??
        //Хотя может это сделано, что бы проверить мою внимательность и догадливость??
        //Делаю удалять все вхождения, несмотря на Case
        return base.replaceAll("(?i)" + remove, "");
    }

    @Override
    public boolean isQuestionString(String text) {
        String lastSymbol = String.valueOf(text.charAt(text.length() - 1));
        return lastSymbol.contains("?");
    }

    @Override
    public String concatenate(String... elements) {
        StringBuilder builder = new StringBuilder();

        for (String strings : elements) {
            if (strings == null) {
                continue;
            }
            //если честно так и не понял почему - Код пишем в {…} даже если он состоит из одной строки.
            //ведь так хорошо читается if(strings == null) continue; ИМХО
            builder.append(strings);
        }

        return builder.toString();
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     * <p>
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {
        return null; //TODO
    }

    @Override
    public boolean isPalindrome(String string) {
        if (string == null) {
            return false;//Хотя может нада true??
        }
        if (string.equals("")) {
            return false;//Хотя может нада true??
        }
        //Удаляем все кроме букв русского и английского языка, или я не прав?
        string = string.replaceAll("[^A-Za-zА-Яа-я]", "");

        StringBuilder builder = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            String symbol = String.valueOf(string.charAt(i));
            builder.append(symbol);
        }

        //Специально игнорирую Case, т.к. иначе ароза != Ароза, хотя можно было сначала убрать Case из строки, но решил так
        return string.equalsIgnoreCase(builder.toString());
    }
}
