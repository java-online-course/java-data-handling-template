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

        BigDecimal number1 = new BigDecimal(a);
        BigDecimal number2 = new BigDecimal(b);

        BigDecimal number3 = number1.divide(number2, range, RoundingMode.HALF_UP);

        return number3;
    }



    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {

        int count = 0;
        BigInteger number = new BigInteger("0");

        if (range < 2) {
            return new BigInteger("2");
        }

        for (int i = 3; ; i++ ) {

            if (count == range) {
                break;
            }

            int count2 = 0;
            for (int j = 2; j < i; j++ ) {

                if (i%j == 0) { count2 = 0; break;} else {count2++;}

            }

            if (count2 !=0) {count++; number = BigInteger.valueOf(i);}

        }

        return number;
    }
}
