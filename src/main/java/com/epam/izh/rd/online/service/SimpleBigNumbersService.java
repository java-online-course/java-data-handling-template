package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        range = 2;
        BigDecimal bigDecimal1 = new BigDecimal(a);
        BigDecimal bigDecimal2 = new BigDecimal(b);
        BigDecimal result = bigDecimal1.divide(bigDecimal2, range, RoundingMode.HALF_UP);
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
        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        int i = 0;
        while (i < range) {
            one = one.add(two);
            if (one.isProbablePrime(3)) {
                i++;
            }
        }
        return one;
    }
}
