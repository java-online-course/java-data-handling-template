package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleTextService;

public class Main {
    public static void main(String[] args) {
        SimpleTextService sm = new SimpleTextService();
        System.out.println(sm.isPalindrome(null));
    }
}
