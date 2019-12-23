package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleRegExpService;

public class SimpleRegExpServiceTest {
    public static void main(String[] args) {
        SimpleRegExpService simpleRegExpService = new SimpleRegExpService();
        //Все делается, но тест не проходит
        System.out.println(simpleRegExpService.maskSensitiveData());
        System.out.println(simpleRegExpService.replacePlaceholders(1, 2));
    }


}
