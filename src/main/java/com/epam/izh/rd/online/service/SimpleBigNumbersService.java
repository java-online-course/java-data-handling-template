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
        BigDecimal bigDecimalA = BigDecimal.valueOf(a);
        BigDecimal bigDecimalB = BigDecimal.valueOf(b);
        return bigDecimalA.divide(bigDecimalB, range, RoundingMode.HALF_EVEN);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int i = 0;
        BigInteger bigInteger = new BigInteger("1");
        BigInteger increment = new BigInteger("1");
        while (i <= range) {
            if (bigInteger.isProbablePrime(1000)) {
                i++;
            }
            bigInteger = bigInteger.add(increment);
        }
        return bigInteger.subtract(new BigInteger("1"));
    }
}
