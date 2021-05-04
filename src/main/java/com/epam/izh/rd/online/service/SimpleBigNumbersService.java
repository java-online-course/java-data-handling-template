package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import static java.math.RoundingMode.HALF_UP;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        BigDecimal result;
        BigDecimal ba = new BigDecimal(a);
        BigDecimal bb = new BigDecimal(b);

        result = ba.divide(bb, range, HALF_UP);
        return result;
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger result = new BigInteger("2");
        for (int i = 0; i < range; i++) {
            result = result.nextProbablePrime();
        }
        return result;

    }
}
