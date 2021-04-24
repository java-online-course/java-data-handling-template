package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        BigDecimal aBigDecimal = new BigDecimal(a);
        BigDecimal bBigDecimal = new BigDecimal(b);
        return aBigDecimal.divide(bBigDecimal, range, ROUND_HALF_UP);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int rangeCounter = 0;
        for (int i = 2; ; i++) {
            BigInteger number = BigInteger.valueOf(i);
            boolean flag = false;
            for (BigInteger j = BigInteger.valueOf(1); j.compareTo(number) < 0 ; j.add(BigInteger.valueOf(1))){
                if (number.mod(j) == null){
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                rangeCounter++;
                if (rangeCounter == range){
                    return number;
                }
            }
            number.add(BigInteger.valueOf(1));
        }
    }
}
