package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleRegExpService;

public class SimpleRegExpServiceTest {
    //Специальное не удаляю!!!
    static int count = 0;

    public static void main(String[] args) {
        SimpleRegExpService simpleRegExpService = new SimpleRegExpService();
        System.out.println(simpleRegExpService.maskSensitiveData());
        System.out.println(simpleRegExpService.replacePlaceholders(1, 2));

//        String text = "Вчера вечером со счета номер 4301 0234 2145 2140 был совершен перевод на счет 5042 2012 0532 2043 в размере ${payment_amount} рублей. На счету осталось ${balance} рублей";
//        Pattern pattern = Pattern.compile("(\\s\\d{4})(\\s\\d{4}\\s\\d{4})(\\s\\d{4})");
//        Matcher matcher = pattern.matcher(text);
//        StringBuffer stringBuffer = new StringBuffer();
//        while (matcher.find()) {
//            matcher.appendReplacement(stringBuffer,"$1 **** ****$3");
//        }
//        matcher.appendTail(stringBuffer);
//        System.out.println(stringBuffer);
//        System.out.println(text);

//        String text = "Вчера вечером со счета номер 4301 0234 2145 2140 был совершен перевод на счет 5042 2012 0532 2043 в размере ${payment_amount} рублей. На счету осталось ${balance} рублей";
//        Pattern pattern = Pattern.compile("\\$\\{payment_amount\\}");
//        double paymentAmount = 1;
//        double balance = 2;
//        Matcher matcher = pattern.matcher(text);
//        while (matcher.find()) {
//            text = text.replaceAll("\\$\\{payment_amount\\}", String.format("%.0f", paymentAmount));
//
//        }
//        Pattern pattern1 = Pattern.compile("\\$\\{balance\\}");
//        Matcher matcher1 = pattern1.matcher(text);
//        while (matcher1.find()) {
//            text = text.replaceAll("\\$\\{balance\\}", String.format("%.0f", balance));
//        }
//        System.out.println(text);
    }


}
