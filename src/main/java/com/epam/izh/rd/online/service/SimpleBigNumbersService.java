package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.BigDecimal.ROUND_DOWN;

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
        BigDecimal big = new BigDecimal((double) a / b);

        return big.setScale(range, ROUND_DOWN);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger bigInteger = new BigInteger(String.valueOf(range));
        int longValue = range;
        int prostoeChisclo = 2;
        int count = 0;

        boolean flag = false;
        boolean flag2 = false;
        while (!flag) {

            prostoeChisclo++;
            int count1 = 0;
            for (int i = 2; i < prostoeChisclo; i++) {

                if (prostoeChisclo % i == 0) {
                    count1++;
                    if (count1 > 0) break;
                }
            }
            if (count1 == 0) {
                count++;
                if (count == range) flag = true;
            }

        }

        return BigInteger.valueOf(prostoeChisclo);

    }
}
