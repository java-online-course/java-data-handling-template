package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleBigNumbersService;

//TODO Удалить перед отправкой

public class SimpleBigNumbersServiceTest {
    public static void main(String[] args) {
        SimpleBigNumbersService simpleBigNumbersService = new SimpleBigNumbersService();
        simpleBigNumbersService.getPrecisionNumber(0 , 3, 2);
        System.out.println(simpleBigNumbersService.getPrecisionNumber(1 , 3, 2));
    }
}
