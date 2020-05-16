package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
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
        // точность и метод округления
        MathContext scale = new MathContext(range, RoundingMode.HALF_UP);
        return new BigDecimal(a).divide(new BigDecimal(b), scale);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger prime = new BigInteger("2");
        int counter = 0;
        for (int i = 0; i < range; i++) {
            prime = prime.nextProbablePrime(); // ищет следующий prime
            counter++;
            if (counter == range) break;
        }
        return prime;
    }
}
