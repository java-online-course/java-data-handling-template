package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleBigNumbersService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        SimpleBigNumbersService simpleBigNumbersService = new SimpleBigNumbersService();
        System.out.println(simpleBigNumbersService.getPrimaryNumber(101));
        System.out.println(simpleBigNumbersService.getPrecisionNumber(1,3,2));
    }
}