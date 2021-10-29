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

        BigDecimal bigA = new BigDecimal(a);
        BigDecimal bigB = new BigDecimal(b);
        return  bigA.divide(bigB, range, RoundingMode.HALF_UP);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {

        int size = range;
        int[] primes = new int[range + 1];
        int[] numbers = new int[size];

        for (int i = 0; i < size; i++) {
            numbers[i] = i;
        }

        primes[0] = 2;
        int i = 0;

        while (i < range) {
            int p = primes[i++];

            for (int j = p * 2; j < size; j += p) {
                numbers[j] = 0;
            }

            try {
                while(numbers[p + 1] == 0) {
                    p++;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {

            } finally {
                if (p + 1 >= size) {
                    int[] temp = new int[size * 2];

                    if (size >= 0) System.arraycopy(numbers, 0, temp, 0, size);

                    size *= 2;
                    numbers = temp;

                    for (int j = size / 2; j < size; j++) {
                        numbers[j] = j;
                    }

                    i = 0;
                } else {
                    primes[i] = p + 1;
                }
            }

        }
        return new BigInteger(String.valueOf(primes[range]));

    }
}
