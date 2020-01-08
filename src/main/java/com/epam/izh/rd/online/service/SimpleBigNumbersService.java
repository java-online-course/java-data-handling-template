package com.epam.izh.rd.online.service;

import sun.awt.SunToolkit;

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
        MathContext mathContext = new MathContext(range, RoundingMode.HALF_UP);
        BigDecimal precisionNumber = new BigDecimal(a).divide(new BigDecimal(b), mathContext);
        return precisionNumber;
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        // O(n^2)
        BigInteger number = new BigInteger("2");
        int index = 0;

        BigInteger accumulator = new BigInteger("2");
        for (;;) {
            BigInteger divider = new BigInteger("2");
            for (;;) {
                if (accumulator.equals(divider)) {
                    number = accumulator;
                    index++;
                    break;
                }
                if (accumulator.mod(divider).equals(new BigInteger("0"))) {
                    break;
                }
                divider = divider.add(new BigInteger("1"));
            }
            accumulator = accumulator.add(new BigInteger("1"));
            if (index > range) {
                break;
            }
        }

        return number;
    }
}
