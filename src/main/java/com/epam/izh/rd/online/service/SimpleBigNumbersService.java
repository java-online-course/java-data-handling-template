package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {

        return new BigDecimal(Integer.toString(a)).divide(new BigDecimal(Integer.toString(b)), range, RoundingMode.HALF_UP);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger[] primaryNumbers = new BigInteger[]{new BigInteger("2")};
        BigInteger value = new BigInteger("3");
        while (primaryNumbers.length <= range) {
            Boolean flagPrimary = true;
            int count = 0;
            while (flagPrimary && count < primaryNumbers.length) {
                if (value.remainder(primaryNumbers[count]).compareTo(new BigInteger("0")) == 0) {
                    flagPrimary = false;
                }
                count++;
            }
            if (flagPrimary) {
                primaryNumbers = Arrays.copyOf(primaryNumbers, primaryNumbers.length + 1);
                primaryNumbers[primaryNumbers.length - 1] = value;
            }
            value = value.add(new BigInteger("1"));
        }
        return primaryNumbers[primaryNumbers.length-1];
    }
}
