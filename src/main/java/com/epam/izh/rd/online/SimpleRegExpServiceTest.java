package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleRegExpService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpServiceTest {
    //TODO Удалить перед отправкой
    static int count = 0;

    public static void main(String[] args) {
        SimpleRegExpService simpleRegExpService = new SimpleRegExpService();
        String text = "Вчера вечером со счета номер 4301 0234 2145 2140 был совершен перевод на счет 5042 2012 0532 2043 в размере ${payment_amount} рублей. На счету осталось ${balance} рублей";
        Pattern pattern = Pattern.compile("(\\s\\d{4})(\\s\\d{4}\\s\\d{4})(\\s\\d{4})");
        Matcher matcher = pattern.matcher(text);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer,"$1 **** ****$3");
        }
        matcher.appendTail(stringBuffer);
        System.out.println(stringBuffer);
        System.out.println(text);
    }


}
