package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) throws IOException {
        SimpleRegExpService simpleRegExpService = new SimpleRegExpService();
        System.out.println(simpleRegExpService.maskSensitiveData());
        System.out.println(simpleRegExpService.replacePlaceholders(2,6));
        /*
        SimpleTextService simpleTextService = new SimpleTextService();
        System.out.println(simpleTextService.removeString("Hello, hello, hello, how low?", ", he"));
        System.out.println(simpleTextService.isQuestionString("HHHbgaghaj jjshdsgd hdhdhdh"));
        System.out.println(simpleTextService.concatenate("Hi", " ", "all", " ", "boys"));
        System.out.println(simpleTextService.toJumpCase("First string, second string"));
        System.out.println(simpleTextService.isPalindrome("а роза упала на лапу Азора"));
        System.out.println(simpleTextService.isPalindrome("а роза уп на лапу Азора"));
        System.out.println(simpleTextService.isPalindrome(","));
*/

    }
}