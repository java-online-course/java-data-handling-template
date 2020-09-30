package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.BigDecimal.ROUND_DOWN;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        BigDecimal devidend = BigDecimal.valueOf(a);
        BigDecimal devider = BigDecimal.valueOf(b);
        return devidend.divide(devider, range, ROUND_DOWN);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger prime = BigInteger.valueOf(2);
        for(int i = 0; i < range; i++) {
            prime = prime.nextProbablePrime();
        }
        return prime;
    }
}
