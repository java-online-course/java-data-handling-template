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
        int resDivision = a / b;
        if (b == 0) {
            return BigDecimal.valueOf(0);
        }
        return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), range, RoundingMode.HALF_UP);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger res = new BigInteger("2");
        for (int i = 0; i < range; i++) {
            res = res.nextProbablePrime();
        }
        return res;
    }
}
