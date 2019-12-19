package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleTextService;

//TODO Удалить перед отправкой

public class SimpleTextServiceTest {
    public static void main(String[] args) {
        SimpleTextService simpleTextService = new SimpleTextService();
        System.out.println(simpleTextService.isQuestionString("Hello, hello, hello, how low!"));
        System.out.println(simpleTextService.isQuestionString(",!?"));
        System.out.println(simpleTextService.isQuestionString("dhdhdfh fghfdh fghfg   fdhfdh?"));
        System.out.println(simpleTextService.concatenate("Bobr", " ", "SpeR", " ", "Nash", " ", null, " ", "VeS'", "Hvorost", " "));

    }


}
