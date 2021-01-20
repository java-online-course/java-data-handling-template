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
        BigDecimal num1 = new BigDecimal(String.valueOf(a));
        BigDecimal num2 = new BigDecimal(String.valueOf(b));
        return num1.divide(num2, 2, RoundingMode.HALF_UP);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger number = new BigInteger("2");
        int counter = 0;
        while (true) {
            boolean prime = isPrime(number);

            if (prime) {
                if (counter == range) {
                    break;
                }
                counter++;
            }

            number = number.add(new BigInteger("1"));

        }
        return number;
    }

    public boolean isPrime(BigInteger number) {
        return number.isProbablePrime(100);
    }
}
