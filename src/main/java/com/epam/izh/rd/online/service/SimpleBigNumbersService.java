package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

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
        BigDecimal firstNumber = BigDecimal.valueOf(a);
        BigDecimal secondNumber = BigDecimal.valueOf(b);
        return firstNumber.divide(secondNumber, range, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int[] numbers = new int[range * range];
        ArrayList<BigInteger> arrayNumbers = new ArrayList<>();
        for (int i = 2; i < numbers.length; i++) {
            numbers[i] = i;
            if (BigInteger.valueOf(numbers[i]).isProbablePrime(i)) {
                arrayNumbers.add(BigInteger.valueOf(numbers[i]));
            }
        }
        return arrayNumbers.get(range);
    }
}
