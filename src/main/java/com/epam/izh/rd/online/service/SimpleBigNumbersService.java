package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     *
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        double z = a;
        double x = b;
        double result = z / x;
        BigDecimal result1 = new BigDecimal(result);
        BigDecimal result2 = result1.setScale(range, RoundingMode.FLOOR);
        return result2;
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger TWO = new BigInteger("2");
        BigInteger bi = new BigInteger("1");
        int i = 1;
        range++;
        while (i < range) {
            bi = bi.add(TWO);
            if (bi.isProbablePrime(80)) {
                i++;
            }
        }
       return bi;
    }
}
