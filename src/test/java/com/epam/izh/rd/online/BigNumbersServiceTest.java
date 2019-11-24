package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.BigNumbersService;
import com.epam.izh.rd.online.service.SimpleBigNumbersService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigNumbersServiceTest {

    private static BigNumbersService bigNumbersService;

    @BeforeAll
    static void setup() {
        bigNumbersService = new SimpleBigNumbersService();
    }

    @Test
    @DisplayName("Тест метода BigNumbersService.getPrecisionNumber(int a, int b, int range)")
    void testGetPrecisionNumber() {
        assertEquals(BigDecimal.valueOf(0.33), bigNumbersService.getPrecisionNumber(1, 3, 2),
                "Для вызова метода: bigNumbersService.getPrecisionNumber(1, 3, 2)");
    }

    @Test
    @DisplayName("Тест метода BigNumbersService.getPrimaryNumber(int range)")
    void testGetPrimaryNumber() {
        assertEquals(BigInteger.valueOf(547), bigNumbersService.getPrimaryNumber(100),
                "Для вызова метода: bigNumbersService.getPrimaryNumber(100)");
    }
}
