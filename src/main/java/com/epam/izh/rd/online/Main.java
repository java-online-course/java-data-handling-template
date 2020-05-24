package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleBigNumbersService;
import com.epam.izh.rd.online.service.SimpleRegExpService;

public class Main {

    public static void main(String[] args) {
        SimpleRegExpService simpleRegExpService = new SimpleRegExpService();
        System.out.println(simpleRegExpService.replacePlaceholders(12, 2));
    }
}
