package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.RegExpService;
import com.epam.izh.rd.online.service.SimpleRegExpService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegExpServiceTest {

    private static RegExpService regExpService;

    @BeforeAll
    static void setup() {
        regExpService = new SimpleRegExpService();
    }

    @Test
    @DisplayName("Тест метода RegExpService.maskSensitiveData()")
    void testMaskSensitiveData() {
        assertEquals("Вчера вечером со счета номер 4301 **** **** 2140 был совершен перевод на счет 5042 **** ****" +
                        " 2043 в размере ${payment_amount} рублей. На счету осталось ${balance} рублей",
                regExpService.maskSensitiveData());
    }

    @Test
    @DisplayName("Тест метода RegExpService.maskSensitiveData()")
    void testGetPrecisionNumber() {
        assertEquals("Вчера вечером со счета номер 4301 0234 2145 2140 был совершен перевод на счет 5042 2012 0532 2043" +
                        " в размере 1 рублей. На счету осталось 2 рублей",
                regExpService.replacePlaceholders(1, 2));
    }

}
